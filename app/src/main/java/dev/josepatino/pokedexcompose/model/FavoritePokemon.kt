package dev.josepatino.pokedexcompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_pokemon")
data class FavoritePokemon(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
)
