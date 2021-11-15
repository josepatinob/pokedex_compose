package dev.josepatino.pokedexcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.josepatino.pokedexcompose.model.FavoritePokemon
import dev.josepatino.pokedexcompose.ui.PokeGridCard

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun FavoritePokemon(
    pokemons: List<FavoritePokemon>,
) {
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(pokemons) { pokemon ->
            PokeGridCard(
                name = pokemon.name,
                imageUrl = pokemon.imageUrl,
                onItemClick = {}
            )
        }
    }
}