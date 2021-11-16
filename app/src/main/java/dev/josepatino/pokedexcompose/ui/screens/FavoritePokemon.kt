package dev.josepatino.pokedexcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
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
    onRemoveAll: () -> Unit,
    onRemovePokemon: (String) -> Unit,
    onItemClick: (String) -> Unit
) {
    var editEnabled by remember { mutableStateOf(false) }
    val editText = if (editEnabled) "Stop Editing" else "Edit"

    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(
                onClick = { editEnabled = !editEnabled },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = editText,
                    color = Color.White
                )
            }
            if (editEnabled) {
                TextButton(onClick = { onRemoveAll() }, modifier = Modifier.padding(5.dp)) {
                    Text(text = "Remove All", color = Color.White)
                }
            }
        }
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(pokemons) { pokemon ->
                PokeGridCard(
                    name = pokemon.name,
                    imageUrl = pokemon.imageUrl,
                    onItemClick = onItemClick,
                    hasRemoveFunctionality = true,
                    showRemoveButton = editEnabled,
                    onRemoveClick = onRemovePokemon,
                )
            }
        }
    }
}