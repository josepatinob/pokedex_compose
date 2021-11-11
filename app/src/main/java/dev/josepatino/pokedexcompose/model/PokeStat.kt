package dev.josepatino.pokedexcompose.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeStat(
    @Json(name = "base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: Detail
) {
    val divisor = 120f
    val statPercentage: Float = baseStat / divisor
    val derivedName = when (stat.name) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SP_ATK"
        "special-defense" -> "SP_DEF"
        "speed" -> "SPD"
        else -> "???"
    }
}
