package ru.skillbranch.gameofthrones.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.skillbranch.gameofthrones.data.room.converters.StringListConverter
import ru.skillbranch.gameofthrones.data.room.dao.HouseDao
import ru.skillbranch.gameofthrones.data.room.entities.HouseRoomEntity

@Database(entities = arrayOf(HouseRoomEntity::class), version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class GOTRoomDatabase : RoomDatabase() {

    abstract fun housesDao(): HouseDao

    private class HouseDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    //housesDao.deleteAll()
                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: GOTRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): GOTRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    GOTRoomDatabase::class.java,
                    "got_database"
                ).addCallback(HouseDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}