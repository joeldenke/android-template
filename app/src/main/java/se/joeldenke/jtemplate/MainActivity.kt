package se.joeldenke.jtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.airbnb.android.showkase.models.Showkase
import se.joeldenke.login.loginGraph
import se.joeldenke.theme.ui.component.JSurface
import se.joeldenke.theme.ui.component.JSurfaceInteraction
import se.joeldenke.theme.ui.component.JText
import se.joeldenke.theme.ui.component.JTextResource

sealed interface JScreen {
    val destination: String
    val content: @Composable (NavBackStackEntry) -> Unit
    object Home: JScreen {
        override val destination = "Home"
        override val content: @Composable (NavBackStackEntry) -> Unit
            get() = {
                HomeScreen()
            }
    }
}



@Composable
fun HomeScreen() {
    val context = LocalContext.current
    se.joeldenke.theme.ui.component.JButton(
        onClick = {
            context.startActivity(Showkase.getBrowserIntent(context))
        },
    ) {
        Greeting("J Design System")
    }
}

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
fun NavigationBar(navController: NavController) {
    Row {
        NavigationItem(onClick = { navController.navigate("main") }, modifier = Modifier.weight(1f), label = {
            JText(
                text = JTextResource.Text(
                    "Home"
                )
            )
        })
        NavigationItem(onClick = { navController.navigate("login") }, modifier = Modifier.weight(1f), label = {
            JText(
                text = JTextResource.Text(
                    "Login"
                )
            )
        })
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            se.joeldenke.theme.ui.theme.JDesignSystem {
                val navController = rememberNavController()
                Column {
                    NavHost(
                        navController = navController,
                        startDestination = "main",
                        modifier = Modifier.weight(1f)
                    ) {
                        main()
                        loginGraph()
                    }
                    NavigationBar(navController = navController)
                }
            }
        }
    }
}

fun NavGraphBuilder.main() {
    navigation(startDestination = JScreen.Home.destination, route = "main") {
        composable(JScreen.Home.destination, content = JScreen.Home.content)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    JText(
        text = JTextResource.Text("Showkase $name!"),
        modifier = modifier
    )
}