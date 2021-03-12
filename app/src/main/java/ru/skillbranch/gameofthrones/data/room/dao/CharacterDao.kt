package ru.skillbranch.gameofthrones.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.room.entities.*

@Dao
interface CharacterDao {

    @Query("SELECT id, house_id, name, titles, aliases FROM $CHARACTER_TABLE")
    fun getAllSmallCharacters(): Flow<List<CharacterItem>>

    @Query("SELECT id, house_id, name, titles, aliases FROM $CHARACTER_TABLE WHERE house_id = :house")
    fun getCharactersByHouse(house: String): Flow<List<CharacterItem>>

//    @Query("SELECT * FROM $CHARACTER_TABLE WHERE id = :id")
//    fun getCharactersFullById(id: String): Flow<List<CharacterFull>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterItem: Character)
}