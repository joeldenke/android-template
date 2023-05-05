package se.joeldenke.login

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import se.joeldenke.theme.ui.component.JText
import se.joeldenke.theme.ui.component.JTextResource

@Composable
fun UsernameLoginScreen() {
    Column {
        JText(text = JTextResource.Text("Test login"))
    }
}