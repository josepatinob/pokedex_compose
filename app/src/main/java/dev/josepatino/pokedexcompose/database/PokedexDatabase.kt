package dev.josepatino.pokedexcompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.josepatino.pokedexcompose.model.FavoritePokemon

@Database(
    entities = [FavoritePokemon::class],
    version = 1,
    exportSchema = false
)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun favoritePokemonDao(): FavoritePokemonDao

    companion object {
        @Volatile
        private var instance: PokedexDatabase? = null

        fun getDatabase(
            context: Context,
        ): PokedexDatabase =
            this.instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    PokedexDatabase::class.java,
                    "PokedexDatabase"
                ).build()

                this.instance = instance
                instance
            }
    }
}