package ru.skillbranch.gameofthrones.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.skillbranch.gameofthrones.repositories.CharacterRepository
import ru.skillbranch.gameofthrones.repositories.HouseRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory<R>(private val repository: R): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HouseViewModel::class.java)){

            return HouseViewModel(repository as HouseRepository) as T
        }
        if(modelClass.isAssignableFrom(CharacterViewModel::class.java)){

            return CharacterViewModel(repository as CharacterRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}