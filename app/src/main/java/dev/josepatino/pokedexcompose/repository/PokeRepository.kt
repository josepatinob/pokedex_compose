package dev.josepatino.pokedexcompose.repository

import dev.josepatino.pokedexcompose.api.PokeApi
import dev.josepatino.pokedexcompose.model.Pokemon
import dev.josepatino.pokedexcompose.model.PokemonDetail
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val pokeApi: PokeApi
) {

    suspend fun fetchPokemonList(limit: Int? = null, offset: Int? = null): Pokemon {
        return pokeApi.fetchPokemonList(limit = limit, offset = offset)
    }

    suspend fun fetchPokemonByName(name: String): PokemonDetail {
        return pokeApi.fetchPokemonByName(name)
    }

    suspend fun fetchPokemonByNumber(number: String): PokemonDetail {
        return pokeApi.fetchPokemonByNumber(number)
    }
}