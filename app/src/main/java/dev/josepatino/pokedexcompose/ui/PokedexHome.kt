package dev.josepatino.pokedexcompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PokedexHome(
    onItemClick: () -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .padding(8.dp)
    ) {
        items(10) {
            PokePreview(onItemClick = onItemClick)
        }
    }
}