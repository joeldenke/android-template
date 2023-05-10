package se.joeldenke.jtemplate

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import com.airbnb.android.showkase.models.Showkase
import se.joeldenke.theme.ui.component.JButton
import se.joeldenke.theme.ui.component.JText
import se.joeldenke.theme.ui.component.JTextResource

sealed interface JScreen {
    val destination: String
    val content: @Composable (NavBackStackEntry) -> Unit

    object Home : JScreen {
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
    JButton(
        onClick = {
            context.startActivity(Showkase.getBrowserIntent(context))
        },
    ) {
        JText(
            text = JTextResource.Text("Showkase J Design System!"),
        )
    }
}