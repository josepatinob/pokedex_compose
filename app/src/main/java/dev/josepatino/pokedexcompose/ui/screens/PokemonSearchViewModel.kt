package dev.josepatino.pokedexcompose.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.pokedexcompose.model.PokemonInfo
import dev.josepatino.pokedexcompose.repository.PokeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonSearchViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    private val _searchResult = MutableLiveData<PokemonInfo>()
    val searchResult: LiveData<PokemonInfo> get() = _searchResult

    fun searchPokemon(value: String) = viewModelScope.launch {
        _searchResult.value = pokeRepository.fetchPokemonByNameOrNumber(value)
    }
}