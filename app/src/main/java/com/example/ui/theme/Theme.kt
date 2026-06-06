package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = WarmGreen,
    onPrimary = Color.White,
    primaryContainer = LightGreen,
    onPrimaryContainer = DarkGreen,
    secondary = WarmOrange,
    onSecondary = Color.White,
    secondaryContainer = PaleOrange,
    onSecondaryContainer = Color(0xFF5D1F00),
    background = NaturalCream,
    onBackground = TextDark,
    surface = SoftWhite,
    onSurface = TextDark,
    surfaceVariant = SoftSand,
    onSurfaceVariant = TextLight,
    outline = Color(0xFFCABFA8)
)

private val DarkColorScheme = darkColorScheme(
    primary = LightGreen,
    onPrimary = DarkGreen,
    primaryContainer = WarmGreen,
    onPrimaryContainer = NaturalCream,
    secondary = PaleOrange,
    onSecondary = Color(0xFF5D1F00),
    secondaryContainer = WarmOrange,
    onSecondaryContainer = NaturalCream,
    background = TextDark,
    onBackground = NaturalCream,
    surface = Color(0xFF383531),
    onSurface = NaturalCream,
    surfaceVariant = Color(0xFF4C4842),
    onSurfaceVariant = WarmSlate,
    outline = Color(0xFF7A756D)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Keep it false by default so that our customized warm organic theme works beautifully
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
