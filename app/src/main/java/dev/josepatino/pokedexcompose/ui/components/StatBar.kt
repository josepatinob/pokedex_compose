package dev.josepatino.pokedexcompose.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.josepatino.pokedexcompose.model.PokeStat
import dev.josepatino.pokedexcompose.util.getStatColor

@ExperimentalAnimationApi
@Composable
fun StatBar(pokeStat: PokeStat) {
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    Row(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = pokeStat.stat.name,
            fontWeight = FontWeight.Light,
            color = White,
            fontSize = 14.sp,
            modifier = Modifier.width(55.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(19.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = White)
        ) {
            AnimatedVisibility(visibleState = state) {
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(pokeStat.statPercentage)
                        .clip(shape = CircleShape),
                    color = getStatColor(pokeStat.stat.name)
                ) {
                    Text(
                        text = "${pokeStat.baseStat} / ${pokeStat.divisor.toInt()}",
                        fontWeight = FontWeight.Bold,
                        color = White,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }
    }
}