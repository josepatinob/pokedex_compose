package dev.josepatino.pokedexcompose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.josepatino.pokedexcompose.PokeScreen
import dev.josepatino.pokedexcompose.ui.theme.colorPrimary

@Composable
fun TopBar(onNavigationUp: () -> Unit, pokemonNumber: Int, currentScreen: String) {
    val displayNum = if (pokemonNumber != -1) "#%05d".format(pokemonNumber) else ""
    if (currentScreen == PokeScreen.PokedexHome.name) {
        TopAppBar(
            title = { Text(text = "Pokedex", color = Color.White) },
            backgroundColor = colorPrimary
        )
    } else {
        TopAppBar(
            title = { Text(text = "Pokedex", color = Color.White) },
            backgroundColor = colorPrimary,
            navigationIcon = {
                IconButton(onClick = onNavigationUp) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "arrow back icon",
                        tint = Color.White
                    )
                }
            },
            actions = {
                Text(
                    text = displayNum,
                    modifier = Modifier.padding(end = 10.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        )
    }
}
