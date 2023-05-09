package se.joeldenke.login

import androidx.compose.runtime.Composable
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
            get() = {
                UsernameLoginScreen()
            }
    }
}

fun NavGraphBuilder.loginGraph() {
    navigation(startDestination = LoginNavEntry.UsernameLogin.destination, route = "login") {
        composable(LoginNavEntry.UsernameLogin.destination, content = LoginNavEntry.UsernameLogin.content)
    }
}