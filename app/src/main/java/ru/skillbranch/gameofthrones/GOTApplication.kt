package ru.skillbranch.gameofthrones

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.skillbranch.gameofthrones.additional.Constants.DATABASE_NAME
import ru.skillbranch.gameofthrones.NetworkService.api
import ru.skillbranch.gameofthrones.data.remote.services.GOTService
import ru.skillbranch.gameofthrones.data.room.GOTRoomDatabase
import ru.skillbranch.gameofthrones.repositories.CharacterRepository
import ru.skillbranch.gameofthrones.repositories.HouseRepository

class GOTApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: GOTApplication? = null

        fun applicationContext(): GOTApplication {
            return instance as GOTApplication
        }

        val applicationScope = CoroutineScope(SupervisorJob())

        val database by lazy { GOTRoomDatabase.getDatabase(applicationContext(), applicationScope) }

        val houseRepository by lazy { HouseRepository(database.housesDao(), api) }

        val characterRepository by lazy { CharacterRepository(database.characterDao(), api) }
    }
}

object NetworkService {
    val api by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient().newBuilder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(GOTService::class.java)
    }
}

object DatabaseManager {
    val db = Room.databaseBuilder(
        GOTApplication.applicationContext(),
        GOTRoomDatabase::class.java, DATABASE_NAME
    ).build()
}