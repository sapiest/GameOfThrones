package ru.skillbranch.gameofthrones.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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

    @FlowPreview
    fun getHousesByName(vararg names: String, result: (houses: List<HouseRes>) -> Unit) =
        viewModelScope.launch {
            val flows = mutableListOf<Flow<List<HouseRes>>>()
            for (name in names) {
                val data = repository.getHousesByName(name)
                flows.add(data)
            }

            combine(*(flows.toTypedArray())) { elements ->
                elements.flatMap { it }
            }.collect {
                Log.e("SIZE", it.size.toString())
                result(it)
            }
        }
}