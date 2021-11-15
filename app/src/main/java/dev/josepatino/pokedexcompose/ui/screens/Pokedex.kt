package dev.josepatino.pokedexcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.josepatino.pokedexcompose.model.Detail
import dev.josepatino.pokedexcompose.ui.PokeGridCard
import dev.josepatino.pokedexcompose.ui.components.PokeProgressIndicator
import kotlinx.coroutines.flow.Flow

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Pokedex(
    pokemons: Flow<PagingData<Detail>>,
    onItemClick: (String) -> Unit
) {
    val lazyPokemons: LazyPagingItems<Detail> = pokemons.collectAsLazyPagingItems()

    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        items(lazyPokemons) { pokemon ->
            PokeGridCard(
                name = pokemon?.name ?: "",
                imageUrl = pokemon?.getImageUrl() ?: "",
                onItemClick = onItemClick
            )
        }

        lazyPokemons.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item { PokeProgressIndicator() }
                }
            }
        }
    }
}

// Looks like items is not publicly available for LazyGrid as of 11/12/2021
@ExperimentalFoundationApi
private fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}