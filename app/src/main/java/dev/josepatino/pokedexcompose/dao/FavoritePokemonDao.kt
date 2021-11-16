package dev.josepatino.pokedexcompose.dao

import androidx.room.*
import dev.josepatino.pokedexcompose.model.FavoritePokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePokemonDao {

    @Query("SELECT * FROM favorite_pokemon ORDER BY id asc")
    fun getAll(): Flow<List<FavoritePokemon>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_pokemon WHERE name = :name)")
    fun isPokemonFavorited(name: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: FavoritePokemon)

    @Query("DELETE FROM favorite_pokemon WHERE name = :name")
    suspend fun deletePokemonByName(name: String)

    @Delete
    suspend fun delete(favorite: FavoritePokemon)

    @Query("DELETE FROM favorite_pokemon")
    suspend fun deleteAll()
}