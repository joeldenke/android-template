package se.joeldenke.theme.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun JDesignSystem(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) JDarkColorTheme else JLightColorTheme
    val view = LocalView.current
    if (!LocalInspectionMode.current) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    CompositionLocalProvider(
        LocalColorTheme provides colorScheme
    ) {
        MaterialBridge(
            darkTheme = darkTheme,
            dynamicColor = dynamicColor,
            content = content
        )
    }
}

@Composable
fun MaterialBridge(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
/*
    val colorScheme = JDesignSystem.colorTheme
    val materialColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorScheme(
            primary = colorScheme.primary,
            secondary = colorScheme.secondary
        )
        else -> lightColorScheme(
            primary = colorScheme.primary,
            secondary = colorScheme.secondary
        )
    }
    val typography = JDesignSystem.typography
    val materialTypography = Typography(
        bodySmall = typography.body,
        bodyMedium = typography.body,
        bodyLarge = typography.body,
        headlineLarge = typography.h1,
        headlineMedium = typography.h2,
        headlineSmall = typography.h3,
        labelLarge = typography.title,
        labelMedium = typography.subtitle,
        labelSmall = typography.button
    )
    MaterialTheme(
        colorScheme = materialColorScheme,
        typography = materialTypography,
        content = content
    )*/
    content()
}

object JDesignSystem {
    val typography: JTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val colorTheme: JColorTheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorTheme.current
}