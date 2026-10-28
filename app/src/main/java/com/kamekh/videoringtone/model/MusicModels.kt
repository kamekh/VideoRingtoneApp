package com.kamekh.videoringtone.model

import androidx.annotation.StringRes

enum class MusicSourceType {
    LOCAL_DEVICE,
    BUILT_IN_LIBRARY,
    USER_UPLOADED,
    PARTNER_CATALOG
}

enum class LicenseType {
    LICENSED,
    PREVIEW_ONLY,
    PERSONAL_USE,
    UNKNOWN
}

data class Track(
    val id: String,
    val title: String,
    val artist: String = "Unknown",
    val sourceType: MusicSourceType,
    val genre: String,
    val mood: String,
    val durationMs: Long,
    val localPath: String? = null,
    val previewUrl: String? = null,
    val licenseType: LicenseType = LicenseType.LICENSED,
    val isLicensedForRingtone: Boolean = true,
    val isFavorite: Boolean = false,
    val tags: List<String> = emptyList()
)

data class Playlist(
    val id: String,
    val name: String,
    val mood: String,
    val genre: String,
    val tracks: List<Track>,
    val isBuiltIn: Boolean = true,
    val isUserCreated: Boolean = false
)

enum class UserMood {
    CALM,
    ROMANTIC,
    ENERGETIC,
    COZY,
    PLAYFUL,
    DREAMY,
    SMOOTH,
    MODERN,
    BRIGHT,
    SOFT,
    WARM,
    UPLIFTING
}

enum class VideoStyle {
    NATURE,
    CITY,
    TRAVEL,
    CINEMATIC,
    HOME,
    MODERN,
    RETRO,
    EVENING,
    MINIMAL,
    ROMANTIC
}

data class UserPreferences(
    val favoriteGenres: List<String> = emptyList(),
    val favoriteMoods: List<UserMood> = emptyList(),
    val favoriteVideoStyles: List<VideoStyle> = emptyList(),
    val preferredUseCase: List<String> = emptyList(),
    val profilePublic: Boolean = false,
    val privateByDefault: Boolean = true
)
