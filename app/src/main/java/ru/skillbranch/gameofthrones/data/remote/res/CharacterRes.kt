package ru.skillbranch.gameofthrones.data.remote.res

import android.util.Log
import ru.skillbranch.gameofthrones.data.room.entities.Character
import ru.skillbranch.gameofthrones.data.room.entities.CharacterFull
import ru.skillbranch.gameofthrones.data.room.entities.CharacterItem
import ru.skillbranch.gameofthrones.data.room.entities.RelativeCharacter

data class CharacterRes(
    val url: String,
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val titles: List<String> = listOf(),
    val aliases: List<String> = listOf(),
    val father: String,
    val mother: String,
    val spouse: String,
    val allegiances: List<String> = listOf(),
    val books: List<String> = listOf(),
    val povBooks: List<Any> = listOf(),
    val tvSeries: List<String> = listOf(),
    val playedBy: List<String> = listOf()
) {
    var houseId: String? = null

    private fun getId(url: String) = url.split("/").last()

    fun toCharacterItem() = CharacterItem(
        id = getId(url),
        house = houseId ?: "",
        name = name,
        titles = titles,
        aliases = aliases
    )

    fun toCharacterDatabaseMode(): Character {
        Log.e("id", getId(url))
        return Character(
            id = getId(url),
            name = name,
            gender = gender,
            culture = culture,
            born = born,
            died = died,
            titles = titles,
            aliases = aliases,
            father = getId(father),
            mother = getId(mother),
            spouse = spouse,
            houseId = houseId ?: ""
        )
    }
}