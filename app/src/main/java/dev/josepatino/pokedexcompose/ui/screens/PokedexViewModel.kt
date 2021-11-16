package dev.josepatino.pokedexcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.pokedexcompose.data.PokemonPagingSource
import dev.josepatino.pokedexcompose.model.Detail
import dev.josepatino.pokedexcompose.repository.PokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    // before adding paging
    //    private val _pokemons = MutableLiveData<Pokemon>()
    //    val pokemons: LiveData<Pokemon> get() = _pokemons
    //
    //    // Load data from a suspend fun and mutate state
    //    init {
    //        viewModelScope.launch {
    //            _pokemons.value = pokeRepository.fetchPokemonList()
    //        }
    //    }

    val pokemons: Flow<PagingData<Detail>> = Pager(PagingConfig(pageSize = 1)) {
        PokemonPagingSource(pokeRepository = pokeRepository)
    }.flow.cachedIn(viewModelScope)
}