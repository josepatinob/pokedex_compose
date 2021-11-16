package dev.josepatino.pokedexcompose.repository

import dev.josepatino.pokedexcompose.dao.FavoritePokemonDao
import dev.josepatino.pokedexcompose.model.FavoritePokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritePokemonRepo @Inject constructor(
    private val favoritePokemonDao: FavoritePokemonDao
) {

    fun getAll() = favoritePokemonDao.getAll()

    fun isPokemonFavorited(name: String) = favoritePokemonDao.isPokemonFavorited(name)

    suspend fun insert(favorite: FavoritePokemon) = favoritePokemonDao.insert(favorite)

    suspend fun delete(favorite: FavoritePokemon) = favoritePokemonDao.delete(favorite)

    suspend fun deletePokemonByName(name: String) = favoritePokemonDao.deletePokemonByName(name)

    suspend fun deleteAll() = favoritePokemonDao.deleteAll()
}