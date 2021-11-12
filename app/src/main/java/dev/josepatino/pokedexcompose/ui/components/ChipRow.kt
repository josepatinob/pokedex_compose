package dev.josepatino.pokedexcompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.josepatino.pokedexcompose.model.PokeType

@Composable
fun ChipRow(types: List<PokeType>) {
    if (types.size > 1) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            types.forEach {
                TypeChip(it)
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (types.isNotEmpty()) {
                TypeChip(types[0])
            }
        }
    }
}