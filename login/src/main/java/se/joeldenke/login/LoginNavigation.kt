package se.joeldenke.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

sealed interface LoginNavEntry {
    val destination: String
    val content: @Composable (NavBackStackEntry) -> Unit
    object UsernameLogin: LoginNavEntry {
        override val destination = "UsernameLogin"
        override val content: @Composable (NavBackStackEntry) -> Unit
            get() = { navBackStackEntry ->
                val viewModel = viewModel<UsernameLoginViewModel>(navBackStackEntry)
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                UsernameLoginScreen(
                    uiState = uiState,
                    onUsernameChanged = { viewModel.updateUsername(it) },
                    onPasswordChanged = { viewModel.updatePassword(it) },
                    onSend = {}
                )
            }
    }
}

fun NavGraphBuilder.loginGraph() {
    navigation(
        startDestination = LoginNavEntry.UsernameLogin.destination,
        route = "login"
    ) {
        composable(
            route = LoginNavEntry.UsernameLogin.destination,
            content = LoginNavEntry.UsernameLogin.content
        )
    }
}