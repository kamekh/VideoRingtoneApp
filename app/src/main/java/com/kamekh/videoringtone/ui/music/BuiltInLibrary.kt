package com.kamekh.videoringtone.ui.music

import com.kamekh.videoringtone.model.LicenseType
import com.kamekh.videoringtone.model.MusicSourceType
import com.kamekh.videoringtone.model.Playlist
import com.kamekh.videoringtone.model.Track

val builtInTracks = listOf(
    Track(
        id = "bg_chill_01",
        title = "Soft Morning",
        artist = "Built In",
        sourceType = MusicSourceType.BUILT_IN_LIBRARY,
        genre = "chill",
        mood = "calm",
        durationMs = 180000,
        licenseType = LicenseType.LICENSED,
        isLicensedForRingtone = true,
        tags = listOf("calm", "soft", "morning")
    ),
    Track(
        id = "bg_romantic_01",
        title = "Velvet Night",
        artist = "Built In",
        sourceType = MusicSourceType.BUILT_IN_LIBRARY,
        genre = "ambient",
        mood = "romantic",
        durationMs = 220000,
        licenseType = LicenseType.LICENSED,
        isLicensedForRingtone = true,
        tags = listOf("romantic", "night", "soft")
    ),
    Track(
        id = "bg_energetic_01",
        title = "City Pulse",
        artist = "Built In",
        sourceType = MusicSourceType.BUILT_IN_LIBRARY,
        genre = "electronic",
        mood = "energetic",
        durationMs = 190000,
        licenseType = LicenseType.LICENSED,
        isLicensedForRingtone = true,
        tags = listOf("energetic", "city", "modern")
    )
)

val builtInPlaylists = listOf(
    Playlist(
        id = "pl_chill",
        name = "Chill",
        mood = "calm",
        genre = "lo-fi",
        tracks = builtInTracks,
        isBuiltIn = true
    ),
    Playlist(
        id = "pl_night",
        name = "Night",
        mood = "dreamy",
        genre = "ambient",
        tracks = builtInTracks,
        isBuiltIn = true
    )
)
