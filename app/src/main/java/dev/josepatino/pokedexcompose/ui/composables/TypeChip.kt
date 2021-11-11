package dev.josepatino.pokedexcompose.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.josepatino.pokedexcompose.model.PokeType

@Composable
fun TypeChip(type: PokeType) {
    Surface(modifier = Modifier.clip(shape = CircleShape)) {
        Row(
            modifier = Modifier
                .background(color = type.color)
                .height(28.dp)
                .width(140.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = type.type.name, fontSize = 18.sp, color = Color.White)
        }
    }
}