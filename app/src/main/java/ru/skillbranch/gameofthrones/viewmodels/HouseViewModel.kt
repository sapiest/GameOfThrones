package ru.skillbranch.gameofthrones.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes
import ru.skillbranch.gameofthrones.data.room.entities.HouseRoomEntity
import ru.skillbranch.gameofthrones.repositories.HouseRepository

class HouseViewModel(private val repository: HouseRepository) : ViewModel() {
    //val allHouses: LiveData<List<HouseRes>> = repository.getAllHouses().asLiveData()

    fun insert(house: HouseRoomEntity) = viewModelScope.launch {
        repository.insert(house)
    }

    fun getAllHouses(result: (houses: List<HouseRes>) -> Unit): Job = viewModelScope.launch {
        val data = repository.getAllHouses()
        data.collect {
            result(it)
        }
    }
}