package dev.josepatino.pokedexcompose.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.pokedexcompose.model.Pokemon
import dev.josepatino.pokedexcompose.repository.PokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexHomeViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    private val _pokemons = MutableLiveData<Pokemon>()
    val pokemons: LiveData<Pokemon> get() = _pokemons

    // Load data from a suspend fun and mutate state
    init {
        viewModelScope.launch {
            _pokemons.value = pokeRepository.fetchPokemonList()
        }
    }
}