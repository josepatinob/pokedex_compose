package dev.josepatino.pokedexcompose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import dev.josepatino.pokedexcompose.PokeScreen
import dev.josepatino.pokedexcompose.ui.theme.colorPrimary

@Composable
fun TopBar(
    onNavigationUp: () -> Unit,
    pokemonNumber: Int,
    currentScreen: String,
    palette: Palette? = null,
    onSearchClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    val displayNum = if (pokemonNumber != -1) "#%05d".format(pokemonNumber) else ""
    val barColor = if (palette != null) Color(palette.dominantSwatch?.rgb ?: 0) else colorPrimary

    when {
        currentScreen.startsWith(PokeScreen.Pokedex.name) -> {
            TopAppBar(
                title = { Text(text = "Pokedex", color = Color.White) },
                backgroundColor = colorPrimary,
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "search icon",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { onLogoutClick() }) {
                        Icon(
                            Icons.Filled.Logout,
                            contentDescription = "logout icon",
                            tint = Color.White
                        )
                    }
                }
            )
        }

        currentScreen.startsWith(PokeScreen.PokeDetail.name) -> {
            TopAppBar(
                title = { Text(text = "Detail", color = Color.White) },
                backgroundColor = barColor,
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
                },
                elevation = 0.dp
            )
        }

        (currentScreen.startsWith(PokeScreen.FavoritePokemon.name) || currentScreen.startsWith(
            PokeScreen.PokeAdmin.name
        )) -> {
            TopAppBar(
                title = {
                    Text(
                        text = currentScreen.replaceAfter("/", ""),
                        color = Color.White
                    )
                },
                backgroundColor = colorPrimary,
                navigationIcon = {
                    IconButton(onClick = onNavigationUp) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "arrow back icon",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    }
}
