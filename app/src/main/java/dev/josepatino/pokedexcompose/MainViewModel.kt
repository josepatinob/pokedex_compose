package dev.josepatino.pokedexcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var userIsAuthenticated by mutableStateOf(false)
    var userRole by mutableStateOf<UserRole>(UserRole.User)

    fun updateUserState(isAuthenticated: Boolean, role: UserRole) {
        userIsAuthenticated = isAuthenticated
        userRole = role
    }
}