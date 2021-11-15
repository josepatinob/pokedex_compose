package dev.josepatino.pokedexcompose.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette
import dev.josepatino.pokedexcompose.R
import dev.josepatino.pokedexcompose.model.Detail
import dev.josepatino.pokedexcompose.model.FavoritePokemon
import dev.josepatino.pokedexcompose.model.PokemonInfo
import dev.josepatino.pokedexcompose.model.toFavoritePokemon
import dev.josepatino.pokedexcompose.ui.theme.colorPrimary
import dev.josepatino.pokedexcompose.ui.theme.colorSecondary

@Composable
fun PokeImageCard(
    pokemon: PokemonInfo?,
    onPaletteColorChange: (Palette) -> Unit,
    palette: Palette?,
    isFavorite: Boolean,
    onFavoriteToggle: (FavoritePokemon) -> Unit
) {
    Surface(
        color = Color(palette?.dominantSwatch?.rgb ?: 0),
        modifier = Modifier
            .height(225.dp)
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    bottomStart = 30.dp,
                    bottomEnd = 30.dp
                )
            ),
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            GlideImage(
                imageModel = pokemon?.imageUrl,
                contentScale = ContentScale.FillHeight,
                circularReveal = CircularReveal(duration = 550),
                bitmapPalette = BitmapPalette {
                    onPaletteColorChange(it)
                },
                modifier = Modifier.align(alignment = Alignment.Center)
            )
            IconToggleButton(
                checked = isFavorite, onCheckedChange = {
                    pokemon?.let {
                        onFavoriteToggle(it.toFavoritePokemon())
                    }
                }, modifier = Modifier.align(
                    Alignment.BottomEnd
                )
            ) {
                val tint by animateColorAsState(
                    if (isFavorite) colorPrimary else Color.LightGray
                )
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite Icon",
                    tint = tint
                )
            }
        }
    }
}