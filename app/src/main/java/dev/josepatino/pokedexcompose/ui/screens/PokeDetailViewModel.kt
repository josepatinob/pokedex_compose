package dev.josepatino.pokedexcompose.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.pokedexcompose.model.PokemonDetail
import dev.josepatino.pokedexcompose.repository.PokeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    private val _pokeDetail = MutableLiveData<PokemonDetail>()
    val pokeDetail: LiveData<PokemonDetail> get() = _pokeDetail


    fun fetchPokemonDetails(name: String) = viewModelScope.launch {
        _pokeDetail.value = pokeRepository.fetchPokemonByName(name)
    }
}