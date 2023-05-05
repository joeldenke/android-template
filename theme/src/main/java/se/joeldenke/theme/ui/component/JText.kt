package se.joeldenke.theme.ui.component

import android.content.res.Resources
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import se.joeldenke.theme.ui.theme.JDesignSystem

sealed interface JTextResource {
    class AndroidString(
        @StringRes val stringResId: Int,
        private vararg val formatArgs: Any
    ) : JTextResource {
        override fun getString(resources: Resources): String {
            return resources.getString(stringResId, *formatArgs)
        }
    }

    class AndroidPlural(
        @PluralsRes val pluralResId: Int,
        private val count: Int,
        private vararg val formatArgs: Any
    ) : JTextResource {
        override fun getString(resources: Resources): String {
            return resources.getQuantityString(pluralResId, count, formatArgs)
        }
    }

    class Text(private val text: String) : JTextResource {
        override fun getString(resources: Resources): String {
            return text
        }
    }

    class AnnotatedText(val annotatedText: AnnotatedString) : JTextResource {
        override fun getString(resources: Resources): String {
            return annotatedText.text
        }
    }

    fun getString(resources: Resources): String

    @Composable
    fun getString(): String = getString(LocalContext.current.resources)
}

@Composable
fun JText(
    text: JTextResource,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = JDesignSystem.typography.body
) {
    val textColor = color.takeOrElse { JDesignSystem.colorTheme.body }
    if (text is JTextResource.AnnotatedText) {
        BasicText(
            text = text.annotatedText,
            modifier = modifier,
            style = style.copy(color = textColor),
            softWrap = softWrap,
            maxLines = maxLines,
            overflow = overflow
        )
    } else {
        BasicText(
            text = text.getString(),
            modifier = modifier,
            style = style.copy(color = textColor),
            softWrap = softWrap,
            maxLines = maxLines,
            overflow = overflow
        )
    }
}

@Preview(showBackground = true, name = "JText")
@Composable
fun JTextPreview() {
    JDesignSystem {
        JText(JTextResource.Text("Preview text"))
    }
}