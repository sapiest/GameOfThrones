package ru.skillbranch.gameofthrones.data.room.entities

import androidx.room.*

const val CHARACTER_TABLE = "characters_table"

@Entity(
    tableName = CHARACTER_TABLE,
    foreignKeys = [
//        ForeignKey(
//        entity = Character::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("father")
//    ),
//        ForeignKey(
//        entity = Character::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("mother")
//    ),
        ForeignKey(
            entity = HouseRoomEntity::class,
            parentColumns = arrayOf("house_name"),
            childColumns = arrayOf("house_id"),
            onUpdate = ForeignKey.CASCADE
        )]
)

data class Character(
    @PrimaryKey val id: String,
    val name: String = "",
    val gender: String = "",
    val culture: String = "",
    val born: String = "",
    val died: String = "",
    val titles: List<String> = listOf(),
    val aliases: List<String> = listOf(),
    val father: String = "", //rel
    val mother: String = "", //rel
    val spouse: String = "",
    @ColumnInfo(name = "house_id") val houseId: String//rel
)

//@Entity(foreignKeys = [ ForeignKey(
//    entity = HouseRoomEntity::class,
//    parentColumns = arrayOf("house_name"),
//    childColumns = arrayOf("house")
//)])
data class CharacterItem(
    val id: String,
    @ColumnInfo(name = "house_id") val house: String, //rel
    val name: String,
    val titles: List<String>,
    val aliases: List<String>
)

//@Entity(foreignKeys = [ ForeignKey(
//    entity = HouseRoomEntity::class,
//    parentColumns = arrayOf("house_name"),
//    childColumns = arrayOf("house")
//)])
data class CharacterFull(
    val id: String,
    val name: String,
    val born: String,
    val died: String,
    val titles: List<String>,
    val aliases: List<String>,
    val house: String, //rel

    val father: RelativeCharacter?,
    val mother: RelativeCharacter?
)

//@Entity(foreignKeys = [ForeignKey(
//    entity = HouseRoomEntity::class,
//    parentColumns = arrayOf("house_name"),
//    childColumns = arrayOf("house")
//)])
data class RelativeCharacter(
    val id: String,
    val name: String,
    val house: String //rel
)