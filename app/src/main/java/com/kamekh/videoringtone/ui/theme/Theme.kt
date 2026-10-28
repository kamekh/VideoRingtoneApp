package com.kamekh.videoringtone.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF5C6AC4),
    secondary = Color(0xFF7B61FF),
    tertiary = Color(0xFF4CB5AE),
    background = Color(0xFFF5F7FF),
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = Color(0xFF111827),
    onSurface = Color(0xFF111827),
    surfaceVariant = Color(0xFFE9EDFF)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF8EA1FF),
    secondary = Color(0xFFB190FF),
    tertiary = Color(0xFF68D5CF),
    background = Color(0xFF101827),
    surface = Color(0xFF1F2937),
    onPrimary = Color.White,
    onBackground = Color(0xFFE5E7EB),
    onSurface = Color(0xFFE5E7EB),
    surfaceVariant = Color(0xFF263244)
)

@Composable
fun VideoRingtoneTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
