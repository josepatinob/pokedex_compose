package dev.josepatino.pokedexcompose.repository

import dev.josepatino.pokedexcompose.api.PokeApi
import dev.josepatino.pokedexcompose.model.Pokemon
import dev.josepatino.pokedexcompose.model.PokemonInfo
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val pokeApi: PokeApi
) {

    suspend fun fetchPokemonList(limit: Int, offset: Int): Pokemon {
        return pokeApi.fetchPokemonList(limit = limit, offset = offset)
    }

    suspend fun fetchPokemonByNameOrNumber(value: String): PokemonInfo {
        return pokeApi.fetchPokemonByName(value)
    }
}