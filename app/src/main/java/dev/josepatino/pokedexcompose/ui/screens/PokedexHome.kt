package dev.josepatino.pokedexcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import dev.josepatino.pokedexcompose.model.Detail
import dev.josepatino.pokedexcompose.ui.PokeGridCard

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PokedexHome(
    pokemonList: List<Detail>,
    onItemClick: (String) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        items(pokemonList) { pokemon ->
            PokeGridCard(
                name = pokemon.name,
                imageUrl = pokemon.getImageUrl(),
                onItemClick = onItemClick
            )
        }
    }
}