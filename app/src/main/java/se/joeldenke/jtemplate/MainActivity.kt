package se.joeldenke.jtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.airbnb.android.showkase.models.Showkase
import se.joeldenke.jtemplate.ui.component.JSurface
import se.joeldenke.jtemplate.ui.component.JSurfaceInteraction
import se.joeldenke.jtemplate.ui.component.JText
import se.joeldenke.jtemplate.ui.component.JTextResource
import se.joeldenke.jtemplate.ui.theme.JDesignSystem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JDesignSystem {
                JSurface(
                    interaction = JSurfaceInteraction.Clickable(
                        onClick = {
                            startActivity(Showkase.getBrowserIntent(this))
                        }
                    ),
                    color = JDesignSystem.colorTheme.primary
                ) {
                    Greeting("J Design System")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    JText(
        text = JTextResource.Text("Showkase $name!"),
        modifier = modifier
    )
}