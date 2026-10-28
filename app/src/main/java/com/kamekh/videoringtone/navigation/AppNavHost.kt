package com.kamekh.videoringtone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kamekh.videoringtone.ui.creation.RingtoneCreationScreen
import com.kamekh.videoringtone.ui.music.MusicSelectionScreen
import com.kamekh.videoringtone.ui.onboarding.PrivacyModeSelectionScreen
import com.kamekh.videoringtone.ui.onboarding.PrivacyScreen
import com.kamekh.videoringtone.ui.onboarding.RulesScreen
import com.kamekh.videoringtone.ui.onboarding.WelcomeScreen
import com.kamekh.videoringtone.ui.profile.UserPreferencesScreen

sealed class AppScreen(val route: String) {
    object Welcome : AppScreen("welcome")
    object Rules : AppScreen("rules")
    object Privacy : AppScreen("privacy")
    object ModeSelection : AppScreen("mode_selection")
    object Preferences : AppScreen("preferences")
    object Music : AppScreen("music")
    object Creation : AppScreen("creation")
    object Home : AppScreen("home")
    object Settings : AppScreen("settings")
    object Security : AppScreen("security")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreen.Welcome.route
    ) {
        composable(AppScreen.Welcome.route) {
            WelcomeScreen(onStart = { navController.navigate(AppScreen.Rules.route) })
        }

        composable(AppScreen.Rules.route) {
            RulesScreen(onAccept = { navController.navigate(AppScreen.Privacy.route) })
        }

        composable(AppScreen.Privacy.route) {
            PrivacyScreen(onContinue = { navController.navigate(AppScreen.ModeSelection.route) })
        }

        composable(AppScreen.ModeSelection.route) {
            PrivacyModeSelectionScreen(onContinue = { isPrivate ->
                navController.navigate(AppScreen.Preferences.route) {
                    popUpTo(AppScreen.ModeSelection.route) { inclusive = true }
                }
            })
        }

        composable(AppScreen.Preferences.route) {
            UserPreferencesScreen {
                navController.navigate(AppScreen.Music.route) {
                    popUpTo(AppScreen.Preferences.route) { inclusive = true }
                }
            }
        }

        composable(AppScreen.Music.route) {
            MusicSelectionScreen {
                navController.navigate(AppScreen.Creation.route) {
                    popUpTo(AppScreen.Music.route) { inclusive = true }
                }
            }
        }

        composable(AppScreen.Creation.route) {
            RingtoneCreationScreen {
                navController.navigate(AppScreen.Home.route) {
                    popUpTo(AppScreen.Creation.route) { inclusive = true }
                }
            }
        }

        composable(AppScreen.Home.route) {
            // Placeholder Home Screen
            WelcomeScreen(onStart = {})
        }
    }
}
