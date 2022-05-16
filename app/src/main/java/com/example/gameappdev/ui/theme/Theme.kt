package com.example.gameappdev.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

// Dark theme

private val DarkColorPalette = darkColors(
    primary = MistyColor,
    primaryVariant = MistyColor,
    secondary = YellowyColor,
    background = YellowyColor
)

// Light theme

private val LightColorPalette = lightColors(
    primary = YellowyColor,
    primaryVariant = YellowyColor,
    secondary = MistyColor,
    background = MistyColor
)

// Old app theme composable not needed (keeping just in case)

@Composable
fun GameAppDevTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}