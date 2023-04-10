package dev.josepatino.pokedexcompose.database

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.josepatino.pokedexcompose.model.Detail
import dev.josepatino.pokedexcompose.repository.PokeRepository
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val pokeRepository: PokeRepository
) : PagingSource<Int, Detail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Detail> {
        return try {
            val nextPage = params.key ?: 0
            val response = pokeRepository.fetchPokemonList(offset = nextPage, limit = 20)

            LoadResult.Page(
                data = response.results,
                prevKey = response.prevPage,
                nextKey = response.nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Detail>): Int? {
        return state.anchorPosition
    }
}