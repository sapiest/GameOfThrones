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
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "region") val region: String,
    @ColumnInfo(name = "coat_Of_Arms") val coatOfArms: String,
    @ColumnInfo(name = "words") val words: String,
    @ColumnInfo(name = "titles") val titles: List<String>,
    @ColumnInfo(name = "seats") val seats: List<String>,
    @ColumnInfo(name = "currentLord") val currentLord: String,
    @ColumnInfo(name = "heir") val heir: String,
    @ColumnInfo(name = "overlord") val overlord: String,
    @ColumnInfo(name = "founded") val founded: String,
    @ColumnInfo(name = "founder") val founder: String,
    @ColumnInfo(name = "diedOut") val diedOut: String,
    @ColumnInfo(name = "ancestralWeapons") val ancestralWeapons: List<String>,
    @ColumnInfo(name = "cadetBranches") val cadetBranches: List<String>,
    @ColumnInfo(name = "swornMembers") val swornMembers: List<String>
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

