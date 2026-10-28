package com.kamekh.videoringtone.model

import androidx.compose.runtime.Immutable

@Immutable
data class UserPreferences(
    val favoriteGenres: List<String> = emptyList(),
    val favoriteMoods: List<String> = emptyList(),
    val favoriteVideoStyles: List<String> = emptyList(),
    val preferredUseCases: List<String> = emptyList(),
    val profilePublic: Boolean = false,
    val privateByDefault: Boolean = true
)
