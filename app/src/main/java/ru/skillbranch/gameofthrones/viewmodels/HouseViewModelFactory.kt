package ru.skillbranch.gameofthrones.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.skillbranch.gameofthrones.repositories.HouseRepository
import java.lang.IllegalArgumentException

class HouseViewModelFactory(private val repository: HouseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HouseViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return HouseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}