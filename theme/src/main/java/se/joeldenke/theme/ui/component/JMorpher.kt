package se.joeldenke.theme.ui.component

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.graphics.shapes.Morph
import kotlin.math.abs
import kotlin.math.min

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
fun JMorpher(
    modifier: Modifier = Modifier,
    setup: ContentDrawScope.() -> Unit = {},
    sizedMorph: SizedMorph,
    color: Color,
    content: @Composable () -> Unit = {}
) {
    Box(modifier = modifier.drawWithContent {
        setup()
        drawContent()
        sizedMorph.resizeMaybe(size.width, size.height)
        drawPath(
            sizedMorph.morph
                .asPath()
                .asComposePath(),
            color
        )
    }) {
        content()
    }
}