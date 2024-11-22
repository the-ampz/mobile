package dev.ericknathan.ampz.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Primary500,
    onPrimary = Primary950,
    secondary = Secondary900,
    onSecondary = Secondary300,
    surfaceVariant = Secondary800,

    background = Secondary900,
    onBackground = Secondary100,
    secondaryContainer = Primary500.copy(alpha = 0.2f)
)

private val LightColorScheme = lightColorScheme(
    primary = Primary500,
    onPrimary = Primary950,
    secondary = Secondary100,
    onSecondary = Secondary700,
    surfaceVariant = Secondary200,

    background = Secondary50,
    onBackground = Secondary800,
    secondaryContainer = Primary500.copy(alpha = 0.2f)

)

@Composable
fun AmpzTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}