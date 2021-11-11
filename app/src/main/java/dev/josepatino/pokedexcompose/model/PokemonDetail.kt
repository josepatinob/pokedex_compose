package dev.josepatino.pokedexcompose.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetail(
    val id: Int,
    val name: String,

    @Json(name = "base_experience")
    val baseExperience: Int,

    val height: Int,
    val weight: Int,
    val stats: List<PokeStat>,
    val types: List<PokeType>
) {
    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}
