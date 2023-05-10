package se.joeldenke.theme.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.joeldenke.theme.ui.theme.JDesignSystem

@Composable
fun JButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    JSurface(
        interaction = JSurfaceInteraction.Clickable(onClick = onClick),
        border = border,
        modifier = modifier,
        shape = RoundedCornerShape(50)
    ) {
        Row(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            content()
        }
    }
}


@Preview(showBackground = true, name = "JButton")
@Composable
fun JButtonPreview() {
    JDesignSystem {
        JButton(
            onClick = {}
        ) {
            JText(JTextResource.Text("Label"))
        }
    }
}