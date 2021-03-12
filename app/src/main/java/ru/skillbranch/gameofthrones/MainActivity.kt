package ru.skillbranch.gameofthrones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import ru.skillbranch.gameofthrones.viewmodels.CharacterViewModel
import ru.skillbranch.gameofthrones.viewmodels.HouseViewModel
import ru.skillbranch.gameofthrones.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val houseViewModel: HouseViewModel by viewModels {
        ViewModelFactory(GOTApplication.houseRepository)
    }
    private val characterViewModel: CharacterViewModel by viewModels {
        ViewModelFactory(GOTApplication.characterRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        houseViewModel.getAllHouses {
            Toast.makeText(this, "LOADED_Houses", Toast.LENGTH_SHORT).show()
            Log.e("SizeHouses", it.size.toString())
        }

        characterViewModel.getAllCharacters {
            Toast.makeText(this, "LOADED", Toast.LENGTH_SHORT).show()
            Log.e("Size", it.size.toString())
        }
//        houseViewModel.getHousesByName(
//            "House Greyjoy of Pyke",
//            "House Tyrell of Highgarden"
//        ) {
//            Toast.makeText(this, "LOADED", Toast.LENGTH_SHORT).show()
//            val actualCharacters = it.fold(mutableListOf<String>()) { acc, houses ->
//                acc.also { it.addAll(houses.swornMembers) }
//            }
//            Log.e("SIZE_CHAR", actualCharacters.size.toString())
//        }
    }
}