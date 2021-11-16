package dev.josepatino.pokedexcompose.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Detail(
    val name: String,
    val url: String
) {

    fun getImageUrl(): String {
        val number = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$number.png"
    }
}