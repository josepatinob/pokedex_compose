package dev.josepatino.pokedexcompose.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Detail>
) {

    val nextPage: Int =
        next.substringAfter(delimiter = "=").substringBefore(delimiter = "&").toInt()

    val prevPage: Int? =
        if (previous.isNullOrEmpty()) null else previous.substringAfter(delimiter = "=")
            .substringBefore(delimiter = "&").toInt()
}
