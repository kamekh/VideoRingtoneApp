package com.kamekh.videoringtone.data

import android.content.Context
import androidx.core.content.edit

private const val PREFS_NAME = "video_ringtone_prefs"
private const val KEY_SAVED_RINGTONES = "saved_ringtones"

data class StoredRingtone(
    val id: String,
    val title: String,
    val videoUri: String? = null,
    val musicUri: String? = null,
    val loop: Boolean = true,
    val vibrate: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)

class RingtoneRepository(private val context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveRingtone(ringtone: StoredRingtone) {
        val current = load().toMutableList()
        current.removeAll { it.id == ringtone.id }
        current.add(0, ringtone)

        val serialized = current.joinToString("||") { item ->
            listOf(
                item.id,
                item.title,
                item.videoUri.orEmpty(),
                item.musicUri.orEmpty(),
                item.loop.toString(),
                item.vibrate.toString(),
                item.createdAt.toString()
            ).joinToString("::")
        }

        prefs.edit { putString(KEY_SAVED_RINGTONES, serialized) }
    }

    fun load(): List<StoredRingtone> {
        val serialized = prefs.getString(KEY_SAVED_RINGTONES, null) ?: return emptyList()
        return serialized.split("||").filter { it.isNotBlank() }.mapNotNull { item ->
            val parts = item.split("::")
            if (parts.size < 7) return@mapNotNull null

            StoredRingtone(
                id = parts[0],
                title = parts[1],
                videoUri = parts[2].ifBlank { null },
                musicUri = parts[3].ifBlank { null },
                loop = parts[4].toBooleanStrictOrNull() ?: true,
                vibrate = parts[5].toBooleanStrictOrNull() ?: true,
                createdAt = parts[6].toLongOrNull() ?: System.currentTimeMillis()
            )
        }
    }
}
