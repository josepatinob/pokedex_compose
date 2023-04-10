package dev.josepatino.pokedexcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.pokedexcompose.database.PokemonPagingSource
import dev.josepatino.pokedexcompose.model.Detail
import dev.josepatino.pokedexcompose.repository.PokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    val pokemons: Flow<PagingData<Detail>> = Pager(PagingConfig(pageSize = 1)) {
        PokemonPagingSource(pokeRepository = pokeRepository)
    }.flow.cachedIn(viewModelScope)
}