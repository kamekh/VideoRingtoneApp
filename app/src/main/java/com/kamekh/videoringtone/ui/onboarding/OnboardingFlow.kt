package com.kamekh.videoringtone.ui.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun OnboardingFlow() {
    var step by remember { mutableStateOf(0) }
    var isPrivateMode by remember { mutableStateOf(true) }

    when (step) {
        0 -> WelcomeScreen(onStart = { step = 1 })
        1 -> RulesScreen(onAccept = { step = 2 })
        2 -> PrivacyScreen(onContinue = { step = 3 })
        3 -> PrivacyModeSelectionScreen(onContinue = { privateMode ->
            isPrivateMode = privateMode
            step = 4
        })
        else -> {
            // In the final app this would navigate to HomeScreen
            // or show age-check for public mode later.
            if (isPrivateMode) {
                WelcomeScreen(onStart = { /* start app */ })
            } else {
                RulesScreen(onAccept = { /* public mode flow */ })
            }
        }
    }
}
