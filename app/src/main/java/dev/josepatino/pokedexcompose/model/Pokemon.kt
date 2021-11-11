package dev.josepatino.pokedexcompose.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    val count: Int,
    val results: List<Detail>
)
