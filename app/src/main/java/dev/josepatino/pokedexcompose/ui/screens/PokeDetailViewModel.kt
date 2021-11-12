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
class PokeDetailViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    private val _pokeInfo = MutableLiveData<PokemonInfo>()
    val pokeInfo: LiveData<PokemonInfo> get() = _pokeInfo


    fun fetchPokemonDetails(name: String) = viewModelScope.launch {
        _pokeInfo.value = pokeRepository.fetchPokemonByNameOrNumber(name)
    }
}