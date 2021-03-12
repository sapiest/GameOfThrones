package ru.skillbranch.gameofthrones.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes

@Entity(tableName = "houses_table")
class HouseRoomEntity(
    //TODO добавить дополнительные таблички для списков
    @PrimaryKey
    @ColumnInfo(name = "house_name") val name: String,
    val url: String,
    @ColumnInfo(name = "full_name") val fullName: String,
     val region: String,
    @ColumnInfo(name = "coat_Of_Arms") val coatOfArms: String,
    val words: String,
    val titles: List<String>,
    val seats: List<String>,
    @ColumnInfo(name = "current_lord") val currentLord: String,
    val heir: String,
    val overlord: String,
    val founded: String,
    val founder: String,
    @ColumnInfo(name = "died_out") val diedOut: String,
    @ColumnInfo(name = "ancestral_weapons") val ancestralWeapons: List<String>,
    @ColumnInfo(name = "cadet_branches") val cadetBranches: List<String>,
    @ColumnInfo(name = "sworn_members") val swornMembers: List<String>
) {
    fun toBaseModel() = HouseRes(
        url = url,
        name = fullName,
        region = region,
        coatOfArms = coatOfArms,
        words = words,
        titles = titles,
        seats = seats,
        currentLord = currentLord,
        heir = heir,
        overlord = overlord,
        founded = founded,
        diedOut = diedOut,
        ancestralWeapons = ancestralWeapons,
        cadetBranches = cadetBranches,
        swornMembers = swornMembers,
        founder = founder
    )
}

