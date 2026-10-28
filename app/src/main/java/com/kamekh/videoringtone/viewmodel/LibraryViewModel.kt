package com.kamekh.videoringtone.viewmodel

import androidx.lifecycle.ViewModel
import com.kamekh.videoringtone.repository.RingtoneRepository

class LibraryViewModel(
    private val repository: RingtoneRepository
) : ViewModel() {
    fun getSavedRingtones() = repository.getAll()
}
