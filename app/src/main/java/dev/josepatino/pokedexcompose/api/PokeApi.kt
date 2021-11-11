package dev.josepatino.pokedexcompose.api

import dev.josepatino.pokedexcompose.model.Pokemon
import dev.josepatino.pokedexcompose.model.PokemonDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    companion object {
        const val POKE_API_BASE_URL = "https://pokeapi.co/api/v2/"
    }

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int? = 20,
        @Query("offset") offset: Int? = 0
    ): Pokemon

    @GET("pokemon/{name}")
    suspend fun fetchPokemonByName(@Path("name") name: String): PokemonDetail

    @GET("pokemon/{number}")
    suspend fun fetchPokemonByNumber(@Path("number") number: String): PokemonDetail
}