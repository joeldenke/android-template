package se.joeldenke.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

sealed interface SearchNavEntry {
    val destination: String
    val content: @Composable (NavBackStackEntry) -> Unit
    object Search: SearchNavEntry {
        override val destination = "Search"
        override val content: @Composable (NavBackStackEntry) -> Unit
            get() = { navBackStackEntry ->
                val viewModel = viewModel<SearchViewModel>(navBackStackEntry)
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                SearchScreen(
                    uiState = uiState,
                ) { viewModel.searchQuery(it) }
            }
    }
}

fun NavGraphBuilder.searchGraph() {
    navigation(
        startDestination = SearchNavEntry.Search.destination,
        route = "search"
    ) {
        composable(
            route = SearchNavEntry.Search.destination,
            content = SearchNavEntry.Search.content
        )
    }
}