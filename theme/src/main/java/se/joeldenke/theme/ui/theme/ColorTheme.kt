package se.joeldenke.theme.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.airbnb.android.showkase.annotation.ShowkaseColor

@ShowkaseColor
val Purple80 = Color(0xFFD0BCFF)

@ShowkaseColor
val PurpleGrey80 = Color(0xFFCCC2DC)

@ShowkaseColor
val Pink80 = Color(0xFFEFB8C8)

@ShowkaseColor
val Purple40 = Color(0xFF6650a4)

@ShowkaseColor
val PurpleGrey40 = Color(0xFF625b71)

@ShowkaseColor
val Pink40 = Color(0xFF7D5260)

object JLightColorTheme: JColorTheme {
    override val primary = Purple40
    override val secondary = PurpleGrey40
    override val body = Color.Black
    //override val tertiary = Pink40
}

object JDarkColorTheme: JColorTheme {
    override val primary = Purple80
    override val secondary = PurpleGrey80
    override val body = Color.White
    //override val tertiary = Pink80
}

interface JColorTheme {
    val primary: Color
    val secondary: Color
    val body: Color
}

internal val LocalColorTheme = staticCompositionLocalOf<JColorTheme> { JLightColorTheme }