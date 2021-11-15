package dev.josepatino.pokedexcompose.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.josepatino.pokedexcompose.dao.FavoritePokemonDao
import dev.josepatino.pokedexcompose.database.PokedexDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providePokedexDatabase(@ApplicationContext context: Context): PokedexDatabase {
        return PokedexDatabase.getDatabase(context = context)
    }

    @Provides
    fun provideFavoritePokemonDao(
        pokedexDatabase: PokedexDatabase
    ): FavoritePokemonDao {
        return pokedexDatabase.favoritePokemonDao()
    }
}