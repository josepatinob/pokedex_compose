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
import dev.josepatino.pokedexcompose.model.FavoritePokemon
import dev.josepatino.pokedexcompose.model.PokemonInfo
import dev.josepatino.pokedexcompose.ui.components.TopBar
import dev.josepatino.pokedexcompose.ui.screens.*
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
        startDestination = PokeScreen.Pokedex.name,
        modifier = modifier
    ) {
        composable(PokeScreen.PokemonSearch.name) {
            val pokemonSearchViewModel: PokemonSearchViewModel = hiltViewModel()
            val searchResult: PokemonInfo? by pokemonSearchViewModel.searchResult.observeAsState()

            PokemonSearch(
                onNavigationItemClick = {
                    navController.navigate(PokeScreen.Pokedex.name)
                },
                onSearch = {
                    pokemonSearchViewModel.searchPokemon(it)
                },
                onPokemonItemClick = { pokemonName ->
                    navController.navigate("${PokeScreen.PokeDetail.name}/$pokemonName")
                },
                searchResult = searchResult
            )
        }
        composable(PokeScreen.Pokedex.name) {
            val pokeViewModel: PokedexViewModel = hiltViewModel()

            Pokedex(
                pokemons = pokeViewModel.pokemons,
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
            val pokemonInfo: PokemonInfo? by pokeDetailViewModel.pokeInfo.observeAsState(null)

            pokeDetailViewModel.fetchPokemonDetails(pokemonName ?: "")
            onPokeNumChange(pokemonInfo?.id ?: -1)
            val isFavorited: State<Boolean> =
                pokeDetailViewModel.isPokemonFavorited(pokemonName ?: "").collectAsState(
                    initial = false
                )

            PokeDetail(
                pokeInfo = pokemonInfo,
                onPaletteColorChange = onPaletteChange,
                palette = palette,
                isFavorite = isFavorited.value,
                onFavoriteToggle = {
                    if (isFavorited.value) {
                        pokeDetailViewModel.removePokemonFromFavorites(it)
                    } else {
                        pokeDetailViewModel.addPokemonToFavorites(it)
                    }
                }
            )
        }
        composable(PokeScreen.FavoritePokemon.name) {
            val favoritePokemonViewModel: FavoritePokemonViewModel = hiltViewModel()
            val favoritePokemon: List<FavoritePokemon> by favoritePokemonViewModel.favoritePokemon.observeAsState(
                emptyList()
            )
            FavoritePokemon(
                pokemons = favoritePokemon
            )
        }
    }
}