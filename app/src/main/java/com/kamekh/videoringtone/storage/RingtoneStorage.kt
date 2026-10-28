package com.kamekh.videoringtone.storage

import android.content.Context
import java.io.File

data class RingtoneMetadata(
    val title: String,
    val createdAt: Long,
    val durationMs: Long,
    val sourceType: String,
    val isPrivate: Boolean = true
)

class RingtoneStorage(private val context: Context) {

    fun saveRingtone(fileName: String, data: ByteArray, metadata: RingtoneMetadata): File {
        val dir = File(context.filesDir, "ringtones")
        if (!dir.exists()) dir.mkdirs()

        val ringtoneFile = File(dir, fileName)
        ringtoneFile.writeBytes(data)

        val metaFile = File(dir, "${fileName}.meta")
        metaFile.writeText(
            buildString {
                appendLine("title=${metadata.title}")
                appendLine("createdAt=${metadata.createdAt}")
                appendLine("durationMs=${metadata.durationMs}")
                appendLine("sourceType=${metadata.sourceType}")
                appendLine("isPrivate=${metadata.isPrivate}")
            }
        )

        return ringtoneFile
    }

    fun listRingtones(): List<File> {
        val dir = File(context.filesDir, "ringtones")
        return if (dir.exists()) {
            dir.listFiles()?.toList() ?: emptyList()
        } else {
            emptyList()
        }
    }
}
