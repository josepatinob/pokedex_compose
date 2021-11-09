package dev.josepatino.pokedexcompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.josepatino.pokedexcompose.R

@ExperimentalMaterialApi
@Composable
fun PokePreview(onItemClick: () -> Unit) {
    Surface(
        onClick = onItemClick,
        modifier = Modifier
            .padding(8.dp)
            .size(180.dp)
            .clip(shape = RoundedCornerShape(15.dp)),
        color = Color(red = 215, green = 112, blue = 95),
    ) {
        Column(
            Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.charmeleon),
                contentDescription = "pokemon image",
                contentScale = ContentScale.Crop
            )
            Text(
                "Charmeleon", color = Color.White, fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}