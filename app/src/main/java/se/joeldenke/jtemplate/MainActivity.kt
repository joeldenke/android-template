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
    onOpenUserNameLogin: () -> Unit
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
    }
}

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    onOpenHome: () -> Unit,
    onOpenUserNameLogin: () -> Unit
) {
    JDesignSystem {
        Column {
            NavHost(
                navController = navController,
                startDestination = "main",
                modifier = Modifier.weight(1f)
            ) {
                main()
                loginGraph()
            }
            NavigationBar(
                onOpenHome = onOpenHome,
                onOpenUserNameLogin = onOpenUserNameLogin
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
                onOpenUserNameLogin = { navController.navigate("login") }
            )
        }
    }
}

fun NavGraphBuilder.main() {
    navigation(startDestination = JScreen.Home.destination, route = "main") {
        composable(JScreen.Home.destination, content = JScreen.Home.content)
    }
}

@Preview(widthDp = 200, heightDp = 500, showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(onOpenHome = {}, onOpenUserNameLogin = {})
}