package dev.josepatino.pokedexcompose.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.pokedexcompose.model.FavoritePokemon
import dev.josepatino.pokedexcompose.model.PokemonInfo
import dev.josepatino.pokedexcompose.repository.FavoritePokemonRepo
import dev.josepatino.pokedexcompose.repository.PokeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(
    private val pokeRepository: PokeRepository,
    private val favoritePokemonRepo: FavoritePokemonRepo
) : ViewModel() {

    private val _pokeInfo = MutableLiveData<PokemonInfo>()
    val pokeInfo: LiveData<PokemonInfo> get() = _pokeInfo

    fun isPokemonFavorited(name: String) = favoritePokemonRepo.isPokemonFavorited(name)

    fun fetchPokemonDetails(name: String) = viewModelScope.launch {
        _pokeInfo.value = pokeRepository.fetchPokemonByNameOrNumber(name)
    }

    fun addPokemonToFavorites(pokemon: FavoritePokemon) = viewModelScope.launch {
        favoritePokemonRepo.insert(pokemon)
    }

    fun removePokemonFromFavorites(pokemon: FavoritePokemon) = viewModelScope.launch {
        favoritePokemonRepo.delete(pokemon)
    }
}