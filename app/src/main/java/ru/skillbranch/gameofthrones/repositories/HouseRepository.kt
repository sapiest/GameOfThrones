package ru.skillbranch.gameofthrones.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.data.local.entities.House
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes
import ru.skillbranch.gameofthrones.data.remote.services.GOTService
import ru.skillbranch.gameofthrones.data.room.dao.HouseDao
import ru.skillbranch.gameofthrones.data.room.entities.HouseRoomEntity

class HouseRepository(private val houseDao: HouseDao, private val gotService: GOTService) {
    private val allHouses: Flow<List<HouseRoomEntity>> = houseDao.getHouses()
    //private val allHousesFromServer = MediatorLiveData<>

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(house: HouseRoomEntity) {
        houseDao.insert(house)
    }

    private suspend fun getAllHousesFromServer(): List<HouseRes> {
        val allHouses = mutableListOf<HouseRes>()
            var page = 1

            do {
                val result = gotService.getHouse(page++)
                result.forEach { house ->
                    insert(house.toDatabaseModel())
                    allHouses.add(house)
                    Log.e("size", house.url)
                }
            }while (result.isNotEmpty())
        return allHouses
    }

    suspend fun getAllHouses(): Flow<List<HouseRes>> {
       return houseDao.getHouses().map {
            return@map if (it.isEmpty()) {
                getAllHousesFromServer()
            } else {
                it.map {
                    it.toBaseModel()
                }
            }
        }
    }
}