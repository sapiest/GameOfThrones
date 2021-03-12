package ru.skillbranch.gameofthrones.data.remote.services

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes

interface GOTService {
    @GET("houses?pageSize=50")
    suspend fun getHouse(@Query("page") page: Int): List<HouseRes>

    @GET("houses")
    suspend fun getHouseByName(@Query("name") name: String): List<HouseRes>

    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: String): CharacterRes

    @GET("characters?pageSize=50")
    suspend fun getCharacters(@Query("page") page: Int): List<CharacterRes>

    @GET("characters")
    suspend fun getCharacterByName(@Query("name") name: String): List<CharacterRes>
}