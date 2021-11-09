package dev.josepatino.pokedexcompose.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.josepatino.pokedexcompose.R
import dev.josepatino.pokedexcompose.model.PokeStat
import dev.josepatino.pokedexcompose.model.PokeType
import dev.josepatino.pokedexcompose.ui.components.ChipRow
import dev.josepatino.pokedexcompose.ui.components.StatBar
import dev.josepatino.pokedexcompose.ui.components.TypeChip
import dev.josepatino.pokedexcompose.ui.theme.PokedexComposeTheme
import dev.josepatino.pokedexcompose.ui.theme.statGrayBlue

@ExperimentalAnimationApi
@Composable
fun PokeDetail() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = MaterialTheme.colors.background
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            PokeImageCard()
            Text(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 0.dp),
                text = "Charmeleon",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
            Text(
                "Base EXP: 142",
                fontSize = 18.sp,
                color = White,
            )
            Column(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChipRow(PokeType.typeList())
                Row(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Measurement(measurement = "90.5 KG", category = "Weight")
                    Measurement(measurement = "1.7M", category = "Height")
                }
                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = "Base Stats",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                PokeStat.statList().forEach {
                    StatBar(it)
                }
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(top = 55.dp, bottom = 25.dp)
            ) {
                Text(text = "Add to Favorites")
            }
        }
    }
}

@Composable
fun Measurement(measurement: String, category: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = measurement,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = category,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            color = White
        )
    }
}

@Composable
fun PokeImageCard() {
    Surface(
        color = Color(0xFFd6705e),
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
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.charmeleon),
                contentDescription = "pokemon image",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
@Preview(showBackground = true)
fun PokeDetailPrev() {
    PokedexComposeTheme {
        PokeDetail()
    }
}