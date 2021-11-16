package dev.josepatino.pokedexcompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette
import dev.josepatino.pokedexcompose.ui.theme.colorPrimary

@ExperimentalMaterialApi
@Composable
fun PokeGridCard(
    name: String,
    imageUrl: String,
    onItemClick: (String) -> Unit,
    hasRemoveFunctionality: Boolean = false,
    showRemoveButton: Boolean = false,
    onRemoveClick: (String) -> Unit = {},
) {
    var palette by remember { mutableStateOf<Palette?>(null) }

    Box(modifier = Modifier.size(180.dp)) {
        Surface(
            onClick = { onItemClick(name) },
            modifier = Modifier
                .padding(10.dp)
                .size(170.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            color = Color(palette?.dominantSwatch?.rgb ?: 0),
        ) {
            Column(
                Modifier.padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(.825f)
                ) {
                    GlideImage(
                        imageModel = imageUrl,
                        contentScale = ContentScale.Crop,
                        circularReveal = CircularReveal(duration = 350),
                        bitmapPalette = BitmapPalette(
                            useCache = true
                        ) {
                            palette = it
                        }
                    )
                }
                Text(
                    text = name, color = Color.White, fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        if (hasRemoveFunctionality && showRemoveButton) {
            Surface(
                onClick = { onRemoveClick(name) },
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .clip(shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.RemoveCircle,
                    contentDescription = "delete icon",
                    tint = colorPrimary
                )
            }
        }
    }
}