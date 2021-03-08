package ru.skillbranch.gameofthrones.data.remote.res

import ru.skillbranch.gameofthrones.data.room.entities.HouseRoomEntity

data class HouseRes(
    val url: String,
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    val titles: List<String> = listOf(),
    val seats: List<String> = listOf(),
    val currentLord: String,
    val heir: String,
    val overlord: String,
    val founded: String,
    val founder: String,
    val diedOut: String,
    val ancestralWeapons: List<String> = listOf(),
    val cadetBranches: List<String> = listOf(),
    val swornMembers: List<String> = listOf()
) {
    private fun toShortName() = name.split(" ")[1]


    fun toDatabaseModel() = HouseRoomEntity(
        name = toShortName(),
        url = url,
        coatOfArms = coatOfArms,
        founder = founder,
        swornMembers = swornMembers,
        cadetBranches = cadetBranches,
        ancestralWeapons = ancestralWeapons,
        currentLord = currentLord,
        diedOut = diedOut,
        founded = founded,
        fullName = name,
        heir = heir,
        overlord = overlord,
        region = region,
        seats = seats,
        titles = titles,
        words = words
    )
}