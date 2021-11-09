package dev.josepatino.pokedexcompose.model

data class PokeType(
    val type: Detail
) {

    companion object {
        fun typeList() = listOf(
            PokeType(
                Detail("fire", "")
            ),
        )
    }
}


