package dev.josepatino.pokedexcompose.ui.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.pokedexcompose.model.PokemonInfo
import dev.josepatino.pokedexcompose.repository.PokeRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class SearchUIState(
    val isLoading: Boolean,
    val result: PokemonInfo?,
    val error: Exception?
)

@HiltViewModel
class PokemonSearchViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    private val _searchUIState = MutableLiveData<SearchUIState>()
    val searchUIState: LiveData<SearchUIState> get() = _searchUIState

    fun searchPokemon(value: String) = viewModelScope.launch {
        _searchUIState.value = SearchUIState(true, null, null)
        try {
            val result = pokeRepository.fetchPokemonByNameOrNumber(value)
            _searchUIState.value = SearchUIState(
                isLoading = false,
                result = result,
                error = null
            )
        } catch (e: Exception) {
            Log.d("POKEMON_SEARCH_VM", e.toString())
            _searchUIState.value = SearchUIState(isLoading = false, result = null, error = e)
        }
    }
}