package com.kamekh.videoringtone.viewmodel

import androidx.lifecycle.ViewModel
import com.kamekh.videoringtone.model.RingtoneCreationState
import com.kamekh.videoringtone.storage.RingtoneMetadata
import com.kamekh.videoringtone.storage.RingtoneStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RingtoneCreationViewModel(
    private val storage: RingtoneStorage
) : ViewModel() {

    private val _state = MutableStateFlow(RingtoneCreationState())
    val state: StateFlow<RingtoneCreationState> = _state.asStateFlow()

    fun selectVideo(path: String) {
        _state.value = _state.value.copy(
            selectedVideoPath = path,
            errorMessage = null
        )
    }

    fun selectMusic(path: String, musicId: String? = null) {
        _state.value = _state.value.copy(
            selectedMusicPath = path,
            selectedMusicId = musicId,
            errorMessage = null
        )
    }

    fun setDuration(durationMs: Long) {
        _state.value = _state.value.copy(durationMs = durationMs)
    }

    fun togglePreview() {
        _state.value = _state.value.copy(
            previewPlaying = !_state.value.previewPlaying
        )
    }

    fun saveRingtone() {
        val musicPath = _state.value.selectedMusicPath ?: run {
            _state.value = _state.value.copy(errorMessage = "Выберите музыку")
            return
        }

        _state.value = _state.value.copy(
            isSaving = true,
            errorMessage = null
        )

        val sampleData = musicPath.toByteArray()
        val metadata = RingtoneMetadata(
            title = "Custom ringtone",
            createdAt = System.currentTimeMillis(),
            durationMs = _state.value.durationMs,
            sourceType = "local",
            isPrivate = true
        )

        val file = storage.saveRingtone(
            "ringtone_${System.currentTimeMillis()}.mp3",
            sampleData,
            metadata
        )

        _state.value = _state.value.copy(
            isSaving = false,
            saveSuccess = true,
            installStatus = "NOT_ATTEMPTED",
            errorMessage = null,
            showManualInstallDialog = false
        )
    }

    fun installRingtone() {
        if (!_state.value.saveSuccess) {
            _state.value = _state.value.copy(
                installStatus = "MANUAL_INSTALL_REQUIRED",
                showManualInstallDialog = true
            )
            return
        }

        _state.value = _state.value.copy(
            installStatus = "SUCCESS"
        )
    }
}
