package dev.josepatino.pokedexcompose.util

import dev.josepatino.pokedexcompose.ui.theme.*

fun getStatColor(stat: String) = when (stat) {
    "HP" -> statRed
    "ATK" -> statYellow
    "DEF" -> statBlue
    "SPD" -> statGrayBlue
    "SP_ATK" -> statLightGreen
    "SP_DEF" -> statOrange
    else -> colorSecondary
}

fun getTypeColor(type: String) = when (type) {
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