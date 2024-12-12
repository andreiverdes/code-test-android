package com.fueled.technicalchallenge

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.fueled.technicalchallenge.presentation.ui.theme.TechnicalChallengeTheme

@Composable
fun ChallengeComposeApp() {
    TechnicalChallengeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AppNavHost()
        }
    }
}