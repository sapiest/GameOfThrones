package ru.skillbranch.gameofthrones.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.skillbranch.gameofthrones.data.room.entities.HouseRoomEntity

@Dao
interface HouseDao {

    @Query("SELECT * FROM houses_table")
    fun getHouses(): Flow<List<HouseRoomEntity>>

    @Query("SELECT * FROM houses_table WHERE house_name = :name")
    fun getHousesByName(name: String): Flow<List<HouseRoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(house: HouseRoomEntity)

    @Query("DELETE FROM houses_table")
    suspend fun deleteAll()
}