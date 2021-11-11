package dev.josepatino.pokedexcompose.model

import com.squareup.moshi.JsonClass
import dev.josepatino.pokedexcompose.ui.theme.*

@JsonClass(generateAdapter = true)
data class PokeType(
    val slot: Int,
    val type: Detail
) {
    val color = when (type.name) {
        "normal" -> normal
        "fighting" -> fighting
        "flying" -> flying
        "poison" -> poison
        "ground" -> ground
        "rock" -> rock
        "bug" -> bug
        "ghost" -> ghost
        "steel" -> steel
        "fire" -> fire
        "water" -> water
        "grass" -> grass
        "electric" -> electric
        "psychic" -> psychic
        "ice" -> ice
        "dragon" -> dragon
        "dark" -> dark
        "fairy" -> fairy
        "unknown" -> colorSecondary
        "shadow" -> shadow
        else -> colorSecondary
    }
}


