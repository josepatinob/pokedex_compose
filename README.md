# Pokedex Compose

Pokedex Compose is an independent re-write of a similar project by the name
of [Pokedex](https://github.com/skydoves/Pokedex). I am recreating the UI but I am doing it using
Google's new UI framework Jetpack Compose and also adding additional functionalities like a favorite
pokemon list and a pokemon search. I am leveraging the MVVM architecture along with Hilt for
dependency injection and Retrofit for making API calls. Full library list below.

<p align="center">
<img src="/previews/screenshot.png"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- JetPack
    - Lifecycle - dispose of observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
    - Room Persistence - construct a database using the abstract layer.
    - Compose - Android’s modern toolkit for building native UI.
    - Navigation - a framework for navigating between 'destinations' within an Android application.
    - Paging3 - Android library that makkes it easier to load data gradually and gracefully.
- Architecture
    - MVVM Architecture (Model - View - ViewModel)
    - Repository pattern

- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Landscapist](https://github.com/skydoves/Landscapist) - Jetpack Compose image loading library which can fetch and display network images using Glide, Coil, Fresco.
- [Accompanist](https://github.com/google/accompanist) - A group of libraries that aim to supplement Jetpack Compose with features that are commonly required by developers but not yet available.
- [Material-Icons](https://developer.android.com/reference/kotlin/androidx/compose/material/icons/Icons) - Material design icons for your app.

## MAD Score
![summary](/previews/summary.png)

## Architecture
Pokedex Compose is based on MVVM architecture and a repository pattern.

![architecture](/previews/final-architecture.png)

## Open API

<img src="https://user-images.githubusercontent.com/24237865/83422649-d1b1d980-a464-11ea-8c91-a24fdf89cd6b.png" align="right" width="21%"/>

Pokedex Compose uses the [PokeAPI](https://pokeapi.co/) for fetching Pokemon related information via a RESTful API interface.

## Find this repository useful? :heart:
__[Follow](https://github.com/jpatino37)__ me for more Android builds like this one! 