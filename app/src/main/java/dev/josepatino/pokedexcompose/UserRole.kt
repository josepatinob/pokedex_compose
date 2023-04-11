package dev.josepatino.pokedexcompose

sealed class UserRole {
    object Admin : UserRole()
    object User : UserRole()
    object Guest : UserRole()
}
