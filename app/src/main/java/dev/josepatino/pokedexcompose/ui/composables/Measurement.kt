package dev.josepatino.pokedexcompose.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Measurement(measurement: String, category: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = measurement,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = category,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            color = Color.White
        )
    }
}