package dev.josepatino.pokedexcompose.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.palette.graphics.Palette
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.josepatino.pokedexcompose.PokeScreen
import dev.josepatino.pokedexcompose.ui.components.TopBar
import dev.josepatino.pokedexcompose.ui.theme.colorPrimary

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PokedexApp() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry.value?.destination?.route

    var topBarPalette by remember { mutableStateOf<Palette?>(null) }
    fun setTopBarPalette(value: Palette?) {
        topBarPalette = value
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = colorPrimary
    )
    LaunchedEffect(topBarPalette) {
        val statusBarColor = if (topBarPalette != null) Color(
            topBarPalette?.dominantSwatch?.rgb ?: 0
        ) else colorPrimary
        // updates color of status bar
        systemUiController.setStatusBarColor(
            color = statusBarColor
        )
    }

    var pokemonNumber by remember { mutableStateOf(-1) }
    fun setPokeNumber(value: Int) {
        pokemonNumber = value
    }

    Scaffold(
        topBar = {
            if (currentScreen != PokeScreen.PokemonSearch.name) {
                TopBar(
                    onNavigationUp = {
                        setTopBarPalette(null)
                        navController.navigateUp()
                    },
                    pokemonNumber = pokemonNumber,
                    currentScreen = currentScreen ?: "",
                    palette = topBarPalette,
                    onSearchClick = {
                        navController.navigate(PokeScreen.PokemonSearch.name)
                    },
                    onFavoriteClick = {
                        navController.navigate(PokeScreen.FavoritePokemon.name)
                    }
                )
            }
        },
    ) { innerPadding ->
        PokedexNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            onPokeNumChange = {
                setPokeNumber(it)
            },
            onPaletteChange = {
                setTopBarPalette(it)
            },
            palette = topBarPalette
        )
    }
}