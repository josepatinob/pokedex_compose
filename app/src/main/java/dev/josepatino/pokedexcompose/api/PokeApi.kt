package dev.josepatino.pokedexcompose.api

import dev.josepatino.pokedexcompose.model.Pokemon
import dev.josepatino.pokedexcompose.model.PokemonInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    companion object {
        const val POKE_API_BASE_URL = "https://pokeapi.co/api/v2/"
    }

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Pokemon

    @GET("pokemon/{value}")
    suspend fun fetchPokemonByNameOrNumber(@Path("value") value: String): PokemonInfo
}