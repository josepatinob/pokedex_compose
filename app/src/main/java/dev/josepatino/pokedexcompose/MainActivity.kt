package dev.josepatino.pokedexcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.SecureCredentialsManager
import com.auth0.android.authentication.storage.SharedPreferencesStorage
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import dagger.hilt.android.AndroidEntryPoint
import dev.josepatino.pokedexcompose.ui.PokedexApp
import dev.josepatino.pokedexcompose.ui.theme.PokedexComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var account: Auth0
    private lateinit var credentialsManager: SecureCredentialsManager

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        account = Auth0(
            getString(R.string.com_auth0_client_id),
            getString(R.string.com_auth0_domain)
        )

        credentialsManager = SecureCredentialsManager(
            this,
            AuthenticationAPIClient(account),
            SharedPreferencesStorage(this)
        )

        val hasValid = credentialsManager.hasValidCredentials()
        val role = if (hasValid) UserRole.Guest else UserRole.User
        mainViewModel.updateUserState(hasValid, role)

        setContent {
            PokedexComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PokedexApp(
                        login = { login() },
                        logout = { logout() },
                        userIsAuthenticated = mainViewModel.userIsAuthenticated,
                        userRole = mainViewModel.userRole
                    )
                }
            }
        }
    }

    fun login() {
        WebAuthProvider.login(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .start(this, object : Callback<Credentials, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                    Log.d("AUTH0", "FAILURE OCCURRED!")
                }

                override fun onSuccess(result: Credentials) {
                    credentialsManager.saveCredentials(result)
                    mainViewModel.updateUserState(true, UserRole.Guest)
                }
            })
    }

    fun logout() {
        WebAuthProvider.logout(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .start(this, object : Callback<Void?, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                    Log.d("AUTH0", "FAILURE OCCURRED!")
                }

                override fun onSuccess(result: Void?) {
                    credentialsManager.clearCredentials()
                    mainViewModel.updateUserState(false, UserRole.User)
                }
            })
    }
}

