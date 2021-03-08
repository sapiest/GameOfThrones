package ru.skillbranch.gameofthrones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import ru.skillbranch.gameofthrones.repositories.RootRepository
import ru.skillbranch.gameofthrones.viewmodels.HouseViewModel
import ru.skillbranch.gameofthrones.viewmodels.HouseViewModelFactory

class MainActivity : AppCompatActivity() {
    private val houseViewModel: HouseViewModel by viewModels {
        HouseViewModelFactory(GOTApplication.repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        houseViewModel.getAllHouses {
//            Log.e("HOUSES", it.size.toString())
//            Toast.makeText(this, "LOADED", Toast.LENGTH_SHORT).show()
//        }
        houseViewModel.getHousesByName(
            "House Greyjoy of Pyke",
            "House Tyrell of Highgarden"
        ) {
            Toast.makeText(this, "LOADED", Toast.LENGTH_SHORT).show()
            val actualCharacters = it.fold(mutableListOf<String>()) { acc, houses ->
                acc.also { it.addAll(houses.swornMembers) }
            }
            Log.e("SIZE_CHAR", actualCharacters.size.toString())
        }
    }
}