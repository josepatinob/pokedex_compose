package dev.josepatino.pokedexcompose.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.palette.graphics.Palette
import dev.josepatino.pokedexcompose.PokeScreen
import dev.josepatino.pokedexcompose.model.FavoritePokemon
import dev.josepatino.pokedexcompose.model.PokemonInfo
import dev.josepatino.pokedexcompose.ui.screens.*

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
            val searchUIState: SearchUIState by pokemonSearchViewModel.searchUIState.observeAsState(
                SearchUIState(false, null, null)
            )

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
                searchUIState = searchUIState
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
                pokemons = favoritePokemon,
                onRemoveAll = { favoritePokemonViewModel.removeAll() },
                onRemovePokemon = { favoritePokemonViewModel.removePokemonByName(it) },
                onItemClick = { pokemonName ->
                    navController.navigate("${PokeScreen.PokeDetail.name}/$pokemonName")
                }
            )
        }
        composable(PokeScreen.PokeAdmin.name) {
            PokeAdmin()
        }
    }
}