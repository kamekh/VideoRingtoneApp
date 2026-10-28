package com.kamekh.videoringtone.ui.creation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kamekh.videoringtone.viewmodel.RingtoneCreationViewModel

@Composable
fun RingtoneCreationScreen(
    viewModel: RingtoneCreationViewModel
) {
    val state by viewModel.state.collectAsState()

    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.18f),
            MaterialTheme.colorScheme.background
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Создать рингтон",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text("Видео: ${state.selectedVideoPath ?: "не выбрано"}")
        Text("Музыка: ${state.selectedMusicPath ?: "не выбрана"}")
        Text("Длительность: ${state.durationMs / 1000} сек")

        if (state.errorMessage != null) {
            Text(state.errorMessage, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = { viewModel.togglePreview() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(if (state.previewPlaying) "Остановить предпросмотр" else "Предпросмотр")
        }

        Button(
            onClick = { viewModel.saveRingtone() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(if (state.isSaving) "Сохранение..." else "Сохранить")
        }

        Button(
            onClick = { viewModel.installRingtone() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Установить как рингтон")
        }

        Text(
            text = "Статус установки: ${state.installStatus}",
            fontSize = 13.sp
        )
    }
}
