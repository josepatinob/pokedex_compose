package dev.josepatino.pokedexcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.josepatino.pokedexcompose.model.PokemonInfo
import dev.josepatino.pokedexcompose.ui.PokeGridCard
import dev.josepatino.pokedexcompose.ui.theme.colorPrimary
import dev.josepatino.pokedexcompose.ui.theme.statLightGreen

@ExperimentalMaterialApi
@Composable
fun SearchHome(
    onNavigationItemClick: () -> Unit,
    onSearch: (String) -> Unit,
    searchResult: PokemonInfo?,
    onPokemonItemClick: (String) -> Unit
) {
    var searchValue by remember { mutableStateOf("") }
    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = "What Pokemon are you looking for?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchValue,
                    onValueChange = { searchValue = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search icon"
                        )
                    },
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .width(250.dp),
                    shape = CircleShape,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = colorPrimary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    placeholder = { Text(text = "Search by name") }
                )
                Button(
                    onClick = { onSearch(searchValue.lowercase()) },
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Text(text = "Search")
                }
            }
            Column(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Results", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                if (searchResult != null) {
                    PokeGridCard(
                        name = searchResult.name,
                        imageUrl = searchResult.imageUrl,
                        onItemClick = {
                            onPokemonItemClick(searchResult.name)
                        })
                } else {
                    Text("No results... please try again!")
                }
                Divider(
                    thickness = 3.dp,
                    modifier = Modifier.padding(top = 15.dp),
                    color = colorPrimary
                )
                Button(
                    onClick = { onNavigationItemClick() },
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .size(width = 250.dp, height = 50.dp),
                ) {
                    Text("Go to Pokedex")
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Arrow right icon"
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun SearchHomePreview() {
    SearchHome({}, {}, null, {})
}