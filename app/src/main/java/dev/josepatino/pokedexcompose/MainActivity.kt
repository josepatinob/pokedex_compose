package dev.josepatino.pokedexcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.josepatino.pokedexcompose.ui.PokeDetail
import dev.josepatino.pokedexcompose.ui.PokedexHome
import dev.josepatino.pokedexcompose.ui.components.TopBar
import dev.josepatino.pokedexcompose.ui.theme.PokedexComposeTheme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PokedexApp()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PokedexApp() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry.value?.destination?.route

    var pokemonNumber by remember { mutableStateOf(-1) }
    fun setPokeNumber(value: Int) {
        pokemonNumber = value
    }

    Scaffold(
        topBar = {
            TopBar(
                onNavigationUp = { navController.navigateUp() },
                pokemonNumber = pokemonNumber,
                currentScreen = currentScreen ?: ""
            )
        },
    ) { innerPadding ->
        PokedexNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            onPokeNumChange = {
                setPokeNumber(it)
            }
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun PokedexNavHost(
    navController: NavHostController, modifier: Modifier = Modifier, onPokeNumChange: (Int) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = PokeScreen.PokedexHome.name,
        modifier = modifier
    ) {
        composable(PokeScreen.PokedexHome.name) {
            PokedexHome(
                onItemClick = {
                    onPokeNumChange(123)
                    navController.navigate(PokeScreen.PokeDetail.name)
                }
            )
        }
        composable(PokeScreen.PokeDetail.name) {
            PokeDetail()
        }
    }
}