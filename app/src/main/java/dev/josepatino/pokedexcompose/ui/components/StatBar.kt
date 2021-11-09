package dev.josepatino.pokedexcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatBar(stat: String) {
    Row(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stat,
            fontWeight = FontWeight.Light,
            color = White,
            fontSize = 14.sp,
            modifier = Modifier.width(45.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(19.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(50.dp)
                    .background(color = Blue)
                    .clip(RoundedCornerShape(50.dp))
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StatBarPreview() {
    StatBar("Hi")
}