package dev.josepatino.pokedexcompose.model

data class PokeStat(
    val baseStat: Int,
    val stat: Detail
) {
    val divisor = 120f
    val statPercentage: Float = baseStat / divisor

    companion object {
        fun statList() = listOf(
            PokeStat(
                58, Detail("HP", "")
            ),
            PokeStat(
                64, Detail("ATK", "")
            ),
            PokeStat(
                58, Detail("DEF", "")
            ),
            PokeStat(
                80, Detail("SP_ATK", "")
            ),
            PokeStat(
                65, Detail("SP_DEF", "")
            ),
            PokeStat(
                80, Detail("SPD", "")
            ),
        )
    }
}
