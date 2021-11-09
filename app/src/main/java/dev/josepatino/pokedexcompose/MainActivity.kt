package dev.josepatino.pokedexcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.josepatino.pokedexcompose.ui.PokeDetail
import dev.josepatino.pokedexcompose.ui.PokedexHome
import dev.josepatino.pokedexcompose.ui.theme.PokedexComposeTheme

class MainActivity : ComponentActivity() {
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

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PokedexApp() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    Scaffold { innerPadding ->
        PokedexNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun PokedexNavHost(
    navController: NavHostController, modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = PokeScreen.PokeDetail.name,
        modifier = modifier
    ) {
        composable(PokeScreen.PokedexHome.name) {
            PokedexHome(
                onItemClick = { navController.navigate(PokeScreen.PokeDetail.name) }
            )
        }
        composable(PokeScreen.PokeDetail.name) {
            PokeDetail()
        }
    }
}