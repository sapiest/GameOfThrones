package ru.skillbranch.gameofthrones.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes
import ru.skillbranch.gameofthrones.data.room.entities.CharacterItem
import ru.skillbranch.gameofthrones.repositories.CharacterRepository

class CharacterViewModel(private val characterRepository: CharacterRepository): ViewModel() {

    fun insert(character: CharacterRes) = viewModelScope.launch {
        characterRepository.insert(character)
    }

    fun getAllCharacters(result: (houses: List<CharacterItem>) -> Unit): Job = viewModelScope.launch{
        val data = characterRepository.getAllCharacters()
        data.collect {
            result(it)
        }
    }

}