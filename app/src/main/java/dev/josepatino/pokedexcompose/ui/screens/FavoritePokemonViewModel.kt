package dev.josepatino.pokedexcompose.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.pokedexcompose.model.FavoritePokemon
import dev.josepatino.pokedexcompose.repository.FavoritePokemonRepo
import javax.inject.Inject

@HiltViewModel
class FavoritePokemonViewModel @Inject constructor(
    favoritePokemonRepo: FavoritePokemonRepo
) : ViewModel() {

    val favoritePokemon: LiveData<List<FavoritePokemon>> = favoritePokemonRepo.getAll().asLiveData()
}