package dev.josepatino.pokedexcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.palette.graphics.Palette
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.josepatino.pokedexcompose.model.Pokemon
import dev.josepatino.pokedexcompose.model.PokemonDetail
import dev.josepatino.pokedexcompose.ui.screens.PokeDetail
import dev.josepatino.pokedexcompose.ui.screens.PokedexHome
import dev.josepatino.pokedexcompose.ui.screens.PokedexHomeViewModel
import dev.josepatino.pokedexcompose.ui.composables.TopBar
import dev.josepatino.pokedexcompose.ui.screens.PokeDetailViewModel
import dev.josepatino.pokedexcompose.ui.theme.PokedexComposeTheme
import dev.josepatino.pokedexcompose.ui.theme.colorPrimary

@AndroidEntryPoint
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
            TopBar(
                onNavigationUp = {
                    setTopBarPalette(null)
                    navController.navigateUp()
                },
                pokemonNumber = pokemonNumber,
                currentScreen = currentScreen ?: "",
                palette = topBarPalette
            )
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

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun PokedexNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onPokeNumChange: (Int) -> Unit,
    onPaletteChange: (Palette) -> Unit,
    palette: Palette?
) {
    NavHost(
        navController = navController,
        startDestination = PokeScreen.PokedexHome.name,
        modifier = modifier
    ) {
        composable(PokeScreen.PokedexHome.name) {
            val pokeViewModel: PokedexHomeViewModel = hiltViewModel()
            val pokemon: Pokemon? by pokeViewModel.pokemons.observeAsState(null)

            PokedexHome(
                pokemonList = pokemon?.results ?: emptyList(),
                onItemClick = { pokemonName ->
                    navController.navigate("${PokeScreen.PokeDetail.name}/$pokemonName")
                }
            )
        }
        composable(
            route = "${PokeScreen.PokeDetail.name}/{name}",
            arguments = listOf(
                navArgument(name = "name") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            val pokemonName = entry.arguments?.getString("name")
            val pokeDetailViewModel: PokeDetailViewModel = hiltViewModel()
            val pokemonDetail: PokemonDetail? by pokeDetailViewModel.pokeDetail.observeAsState(null)

            pokeDetailViewModel.fetchPokemonDetails(pokemonName ?: "")
            onPokeNumChange(pokemonDetail?.id ?: -1)

            PokeDetail(
                pokeDetail = pokemonDetail,
                onPaletteColorChange = onPaletteChange,
                palette = palette
            )
        }
    }
}