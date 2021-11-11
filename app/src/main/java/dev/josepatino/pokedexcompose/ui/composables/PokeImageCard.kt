package dev.josepatino.pokedexcompose.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette

@Composable
fun PokeImageCard(
    imageUrl: String,
    onPaletteColorChange: (Palette) -> Unit,
    palette: Palette?
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            GlideImage(
                imageModel = imageUrl,
                contentScale = ContentScale.FillHeight,
                circularReveal = CircularReveal(duration = 550),
                bitmapPalette = BitmapPalette {
                    onPaletteColorChange(it)
                }
            )

        }
    }
}