package se.joeldenke.login

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import se.joeldenke.theme.ui.component.JText
import se.joeldenke.theme.ui.component.JTextField
import se.joeldenke.theme.ui.component.JTextResource
import se.joeldenke.theme.ui.theme.JDesignSystem
import kotlin.math.abs
import kotlin.math.min

data class UsernameUiState(
    val username: String = "",
    val password: String = "",
)

class UsernameLoginViewModel: ViewModel() {
    fun updateUsername(value: String) {
        uiState.update { it.copy(username = value) }
    }

    fun updatePassword(value: String) {
        uiState.update { it.copy(password = value) }
    }

    val uiState = MutableStateFlow(UsernameUiState())
}

internal fun calculateMatrix(bounds: RectF, width: Float, height: Float): Matrix {
    val originalWidth = bounds.right - bounds.left
    val originalHeight = bounds.bottom - bounds.top
    val scale = min(width / originalWidth, height / originalHeight)
    val newLeft = bounds.left - (width / scale - originalWidth) / 2
    val newTop = bounds.top - (height / scale - originalHeight) / 2
    val matrix = Matrix()
    matrix.setTranslate(-newLeft, -newTop)
    matrix.postScale(scale, scale)
    return matrix
}
class SizedMorph(val morph: Morph) {
    var width = 1f
    var height = 1f

    fun resizeMaybe(newWidth: Float, newHeight: Float) {
        if (abs(width - newWidth) > 1e-4 || abs(height - newHeight) > 1e-4) {
            val matrix = calculateMatrix(RectF(0f, 0f, width, height), newWidth, newHeight)
            morph.transform(matrix)
            width = newWidth
            height = newHeight
        }
    }
}

@Composable
fun UsernameLoginScreen(
    uiState: UsernameUiState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    prep: ContentDrawScope.() -> Unit = {},
    sizedMorph: SizedMorph,
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
        val shape = RoundedCornerShape(50)
        Box(modifier = Modifier
            .background(
                JDesignSystem.colorTheme.primary,
                shape = shape
            )
            .clip(shape)
            .drawWithContent {
                prep()
                drawContent()
                sizedMorph.resizeMaybe(size.width, size.height)
                drawPath(sizedMorph.morph.asPath().asComposePath(), Color.White)
            }
            .clickable(
                indication = null,
                onClick = onSend,
                interactionSource = MutableInteractionSource()
            )
        ) {
            Row(Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
                JText(text = JTextResource.Text("Send"))
            }
        }
    }
}

@Preview
@Composable
fun UsernameLoginScreenPreview() {
    UsernameLoginScreen(
        uiState = UsernameUiState("test", "none"),
        onUsernameChanged = {},
        onPasswordChanged = {},
        sizedMorph = SizedMorph(Morph(RoundedPolygon(3), RoundedPolygon(5))),
    ) { }
}