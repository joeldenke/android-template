package se.joeldenke.jtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import se.joeldenke.login.loginGraph
import se.joeldenke.search.searchGraph
import se.joeldenke.theme.ui.component.JSurface
import se.joeldenke.theme.ui.component.JSurfaceInteraction
import se.joeldenke.theme.ui.component.JText
import se.joeldenke.theme.ui.component.JTextResource
import se.joeldenke.theme.ui.theme.JDesignSystem


@Composable
fun NavigationItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    icon: @Composable () -> Unit = {}
) {
    JSurface(
        interaction = JSurfaceInteraction.Clickable(
            onClick = onClick
        ), modifier = modifier
    ) {
        Row(Modifier.align(Alignment.Center)) {
            icon()
            label()
        }
    }
}

@Composable
fun NavigationBar(
    onOpenHome: () -> Unit,
    onOpenUserNameLogin: () -> Unit,
    onOpenSearch: () -> Unit
) {
    Row {
        NavigationItem(
            onClick = onOpenHome,
            modifier = Modifier.weight(1f),
            label = {
                JText(
                    text = JTextResource.Text(
                        "Home"
                    )
                )
            }
        )
        NavigationItem(
            onClick = onOpenUserNameLogin,
            modifier = Modifier.weight(1f),
            label = {
                JText(
                    text = JTextResource.Text(
                        "Login"
                    )
                )
            }
        )
        NavigationItem(
            onClick = onOpenSearch,
            modifier = Modifier.weight(1f),
            label = {
                JText(
                    text = JTextResource.Text(
                        "Search"
                    )
                )
            }
        )
    }
}

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    onOpenHome: () -> Unit,
    onOpenUserNameLogin: () -> Unit,
    onOpenSearch: () -> Unit
) {
    JDesignSystem {
        Column {
            NavHost(
                navController = navController,
                startDestination = "main",
                modifier = Modifier.weight(1f)
            ) {
                mainGraph()
                loginGraph()
                searchGraph()
            }
            NavigationBar(
                onOpenHome = onOpenHome,
                onOpenUserNameLogin = onOpenUserNameLogin,
                onOpenSearch = onOpenSearch
            )
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainScreen(
                navController = navController,
                onOpenHome = { navController.navigate("main", bundleOf()) },
                onOpenUserNameLogin = { navController.navigate("login") },
                onOpenSearch = { navController.navigate("search") }
            )
        }
    }
}

fun NavGraphBuilder.mainGraph() {
    navigation(startDestination = JScreen.Home.destination, route = "main") {
        composable(JScreen.Home.destination, content = JScreen.Home.content)
    }
}

@Preview(widthDp = 200, heightDp = 500, showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        onOpenHome = {},
        onOpenUserNameLogin = {},
        onOpenSearch = {}
    )
}