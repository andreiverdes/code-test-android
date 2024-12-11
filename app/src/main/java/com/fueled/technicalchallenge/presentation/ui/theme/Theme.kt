package com.fueled.technicalchallenge.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Dark.Primary,
    onPrimary = Dark.OnPrimary,
    primaryContainer = Dark.PrimaryContainer,
    onPrimaryContainer = Dark.OnPrimaryContainer,
    secondary = Dark.Secondary,
    onSecondary = Dark.OnSecondary,
    secondaryContainer = Dark.SecondaryContainer,
    onSecondaryContainer = Dark.OnSecondaryContainer,
    tertiary = Dark.Tertiary,
    onTertiary = Dark.OnTertiary,
    tertiaryContainer = Dark.TertiaryContainer,
    onTertiaryContainer = Dark.OnTertiaryContainer,
    error = Dark.Error,
    errorContainer = Dark.ErrorContainer,
    onError = Dark.OnError,
    onErrorContainer = Dark.OnErrorContainer,
    background = Dark.Background,
    onBackground = Dark.OnBackground,
    surface = Dark.Surface,
    onSurface = Dark.OnSurface,
    surfaceVariant = Dark.SurfaceVariant,
    onSurfaceVariant = Dark.OnSurfaceVariant,
    outline = Dark.Outline,
    outlineVariant = Dark.OutlineVariant,
    inverseOnSurface = Dark.InverseOnSurface,
)

private val LightColorScheme = lightColorScheme(
    primary = Light.Primary,
    onPrimary = Light.OnPrimary,
    primaryContainer = Light.PrimaryContainer,
    onPrimaryContainer = Light.OnPrimaryContainer,
    secondary = Light.Secondary,
    onSecondary = Light.OnSecondary,
    secondaryContainer = Light.SecondaryContainer,
    onSecondaryContainer = Light.OnSecondaryContainer,
    tertiary = Light.Tertiary,
    onTertiary = Light.OnTertiary,
    tertiaryContainer = Light.TertiaryContainer,
    onTertiaryContainer = Light.OnTertiaryContainer,
    error = Light.Error,
    errorContainer = Light.ErrorContainer,
    onError = Light.OnError,
    onErrorContainer = Light.OnErrorContainer,
    background = Light.Background,
    onBackground = Light.OnBackground,
    surface = Light.Surface,
    onSurface = Light.OnSurface,
    surfaceVariant = Light.SurfaceVariant,
    onSurfaceVariant = Light.OnSurfaceVariant,
    outline = Light.Outline,
    outlineVariant = Light.OutlineVariant,
    inverseOnSurface = Light.InverseOnSurface,
)

@Composable
fun TechnicalChallengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TechnicalChallengeTypography,
        shapes = Shapes,
        content = content
    )
}