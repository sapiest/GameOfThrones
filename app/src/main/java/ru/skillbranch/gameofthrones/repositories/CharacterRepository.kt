package ru.skillbranch.gameofthrones.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.services.GOTService
import ru.skillbranch.gameofthrones.data.room.dao.CharacterDao
import ru.skillbranch.gameofthrones.data.room.entities.Character
import ru.skillbranch.gameofthrones.data.room.entities.CharacterItem
import ru.skillbranch.gameofthrones.data.room.entities.HouseRoomEntity

class CharacterRepository(
    private val characterDao: CharacterDao,
    private val gotService: GOTService
) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(character: CharacterRes) {
        Log.e("characterDatabase", character.toCharacterDatabaseMode().toString())
        characterDao.insert(character.toCharacterDatabaseMode())
    }

    private suspend fun getAllCharactersFromServer(): List<CharacterItem> {
        val allCharacters = mutableListOf<CharacterItem>()
        var page = 1

        do {
            val result = gotService.getCharacters(page++)
            result.forEach {
                Log.e("character", it.toString())
                insert(it)
                allCharacters.add(it.toCharacterItem())
            }
        } while (result.isNotEmpty())
        return allCharacters
    }

    suspend fun getAllCharacters(): Flow<List<CharacterItem>> {
        return characterDao.getAllSmallCharacters().map {
            return@map if (it.isEmpty()) {
                getAllCharactersFromServer()
            } else {
                it
            }
        }
    }
}