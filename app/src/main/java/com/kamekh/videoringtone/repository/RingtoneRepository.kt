package com.kamekh.videoringtone.repository

import com.kamekh.videoringtone.model.RingtoneItem
import com.kamekh.videoringtone.storage.RingtoneStorage

class RingtoneRepository(
    private val storage: RingtoneStorage
) {
    fun getAll(): List<RingtoneItem> {
        return storage.listRingtones().mapIndexed { index, file ->
            RingtoneItem(
                id = "ringtone_${index}",
                title = file.nameWithoutExtension,
                videoPath = null,
                musicPath = file.absolutePath,
                sourceType = "local",
                mood = "calm",
                genre = "chill",
                durationMs = 20000L,
                isPrivate = true,
                isSaved = true,
                installStatus = "NOT_ATTEMPTED"
            )
        }
    }
}
