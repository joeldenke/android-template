package se.joeldenke.login

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.Star
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.coroutines.launch
import se.joeldenke.theme.ui.component.SizedMorph

sealed interface LoginNavEntry {
    val destination: String
    val content: @Composable (NavBackStackEntry) -> Unit
    object UsernameLogin: LoginNavEntry {
        override val destination = "UsernameLogin"
        override val content: @Composable (NavBackStackEntry) -> Unit
            get() = {
                val viewModel = viewModel<UsernameLoginViewModel>()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val progress = remember { Animatable(0f) }
                val pointyTriangle = RoundedPolygon(3)
                val roundedStar = Star(numVerticesPerRadius = 5, innerRadius = .5f, rounding = CornerRounding(radius = .1f))
                val morph = Morph(pointyTriangle, roundedStar)
                val sizedMorph = SizedMorph(morph)
                val coroutineScope = rememberCoroutineScope()
                UsernameLoginScreen(
                    uiState = uiState,
                    onUsernameChanged = { viewModel.updateUsername(it) },
                    onPasswordChanged = { viewModel.updatePassword(it) },
                    sizedMorph = sizedMorph,
                    prep = {
                        sizedMorph.morph.progress = progress.value
                    }
                ) { coroutineScope.launch { doAnimation(progress = progress) }}
            }
    }
}

private suspend fun doAnimation(progress: Animatable<Float, AnimationVector1D>) {
    val end = if (progress.targetValue == 1f) 0f else 1f
    progress.animateTo(
        end,
        animationSpec = spring(0.6f, 50f)
    )
}

fun NavGraphBuilder.loginGraph() {
    navigation(startDestination = LoginNavEntry.UsernameLogin.destination, route = "login") {
        composable(LoginNavEntry.UsernameLogin.destination, content = LoginNavEntry.UsernameLogin.content)
    }
}