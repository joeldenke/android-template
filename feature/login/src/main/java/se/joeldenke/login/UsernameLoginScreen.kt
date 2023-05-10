package se.joeldenke.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import se.joeldenke.theme.ui.component.JButton
import se.joeldenke.theme.ui.component.JText
import se.joeldenke.theme.ui.component.JTextField
import se.joeldenke.theme.ui.component.JTextResource
import se.joeldenke.theme.ui.theme.JDesignSystem

data class UsernameUiState(
    val username: String = "",
    val password: String = "",
)

class UsernameLoginViewModel : ViewModel() {
    fun updateUsername(value: String) {
        uiState.update { it.copy(username = value) }
    }

    fun updatePassword(value: String) {
        uiState.update { it.copy(password = value) }
    }

    val uiState = MutableStateFlow(UsernameUiState())
}

@Composable
fun UsernameLoginScreen(
    uiState: UsernameUiState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSend: () -> Unit
) {
    Column {
        JText(text = JTextResource.Text("Test login"), style = JDesignSystem.typography.h2)
        JText(text = JTextResource.Text("Username"), style = JDesignSystem.typography.caption)
        JTextField(
            value = uiState.username,
            onValueChanged = onUsernameChanged,
        )
        JText(text = JTextResource.Text("Password"), style = JDesignSystem.typography.caption)
        JTextField(
            value = uiState.password,
            onValueChanged = onPasswordChanged,
            transformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        JButton(
            onClick = onSend
        ) {
            Row(Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
                JText(
                    text = JTextResource.Text("Send"),
                    style = JDesignSystem.typography.button,
                    color = JDesignSystem.colorTheme.body
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsernameLoginScreenPreview() {
    UsernameLoginScreen(
        uiState = UsernameUiState("test", "none"),
        onUsernameChanged = {},
        onPasswordChanged = {},
    ) { }
}