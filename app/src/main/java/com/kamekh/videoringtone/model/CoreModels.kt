package com.kamekh.videoringtone.model

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

data class UserPreferences(
    val favoriteGenres: List<String> = emptyList(),
    val favoriteMoods: List<String> = emptyList(),
    val favoriteVideoStyles: List<String> = emptyList(),
    val preferredUseCases: List<String> = emptyList(),
    val profilePublic: Boolean = false,
    val privateByDefault: Boolean = true
)

data class RingtoneItem(
    val id: String,
    val title: String,
    val videoPath: String? = null,
    val musicPath: String? = null,
    val sourceType: String = "local",
    val mood: String = "calm",
    val genre: String = "chill",
    val durationMs: Long = 20000L,
    val createdAt: Long = System.currentTimeMillis(),
    val isPrivate: Boolean = true,
    val isSaved: Boolean = false,
    val installStatus: String = "NOT_ATTEMPTED"
)

data class RingtoneCreationState(
    val selectedVideoPath: String? = null,
    val selectedMusicPath: String? = null,
    val selectedMusicId: String? = null,
    val durationMs: Long = 20000L,
    val previewPlaying: Boolean = false,
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false,
    val installStatus: String = "NOT_ATTEMPTED",
    val errorMessage: String? = null,
    val showManualInstallDialog: Boolean = false
)

enum class InstallStatus {
    NOT_ATTEMPTED,
    SUCCESS,
    FAILED,
    MANUAL_INSTALL_REQUIRED
}
