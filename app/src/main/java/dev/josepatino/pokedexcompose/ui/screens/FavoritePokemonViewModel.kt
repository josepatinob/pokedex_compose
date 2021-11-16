package dev.josepatino.pokedexcompose.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.pokedexcompose.model.FavoritePokemon
import dev.josepatino.pokedexcompose.repository.FavoritePokemonRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritePokemonViewModel @Inject constructor(
    private val favoritePokemonRepo: FavoritePokemonRepo
) : ViewModel() {

    val favoritePokemon: LiveData<List<FavoritePokemon>> = favoritePokemonRepo.getAll().asLiveData()

    fun removeAll() = viewModelScope.launch {
        favoritePokemonRepo.deleteAll()
    }

    fun removePokemonByName(name: String) = viewModelScope.launch {
        favoritePokemonRepo.deletePokemonByName(name)
    }
}