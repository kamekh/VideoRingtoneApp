package com.kamekh.videoringtone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.rememberLauncherForActivityResult
import com.kamekh.videoringtone.data.RingtoneRepository
import com.kamekh.videoringtone.data.StoredRingtone
import com.kamekh.videoringtone.ui.theme.VideoRingtoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VideoRingtoneTheme {
                VideoRingtoneHomeScreen()
            }
        }
    }
}

@Composable
fun VideoRingtoneHomeScreen() {
    val context = LocalContext.current
    val repository = remember { RingtoneRepository(context.applicationContext) }
    val savedRingtones = remember { mutableStateListOf<StoredRingtone>() }

    savedRingtones.clear()
    savedRingtones.addAll(repository.load())

    var selectedVideoName by remember { mutableStateOf("Пока ничего не выбрано") }
    var selectedMusicName by remember { mutableStateOf("Без музыки") }
    var loopEnabled by remember { mutableStateOf(true) }
    var vibrateEnabled by remember { mutableStateOf(true) }

    val videoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                selectedVideoName = uri.lastPathSegment ?: "Выбранное видео"
                val ringtone = StoredRingtone(
                    id = System.currentTimeMillis().toString(),
                    title = selectedVideoName,
                    videoUri = uri.toString(),
                    musicUri = null,
                    loop = loopEnabled,
                    vibrate = vibrateEnabled
                )
                repository.saveRingtone(ringtone)
                savedRingtones.clear()
                savedRingtones.addAll(repository.load())
            }
        }
    )

    val musicPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                selectedMusicName = uri.lastPathSegment ?: "Выбранная музыка"
                val ringtone = StoredRingtone(
                    id = System.currentTimeMillis().toString(),
                    title = selectedVideoName.takeIf { it != "Пока ничего не выбрано" } ?: "Мой рингтон",
                    videoUri = null,
                    musicUri = uri.toString(),
                    loop = loopEnabled,
                    vibrate = vibrateEnabled
                )
                repository.saveRingtone(ringtone)
                savedRingtones.clear()
                savedRingtones.addAll(repository.load())
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "VideoRingtone",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Выберите видео, музыку и установите красивый ringtone для входящего звонка.",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Видео")
                        Button(onClick = { videoPicker.launch("video/*") }) {
                            Text("Выбрать")
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Музыка")
                        Button(onClick = { musicPicker.launch("audio/*") }) {
                            Text("Подобрать")
                        }
                    }

                    Text("Видео: $selectedVideoName")
                    Text("Музыка: $selectedMusicName")
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Настройки", fontWeight = FontWeight.SemiBold)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Повтор")
                        Switch(checked = loopEnabled, onCheckedChange = { loopEnabled = it })
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Вибрация")
                        Switch(checked = vibrateEnabled, onCheckedChange = { vibrateEnabled = it })
                    }

                    Button(
                        onClick = {
                            val ringtone = StoredRingtone(
                                id = System.currentTimeMillis().toString(),
                                title = selectedVideoName.takeIf { it != "Пока ничего не выбрано" }
                                    ?: "Мой рингтон",
                                videoUri = if (selectedVideoName != "Пока ничего не выбрано") "video://selected" else null,
                                musicUri = if (selectedMusicName != "Без музыки") "audio://selected" else null,
                                loop = loopEnabled,
                                vibrate = vibrateEnabled
                            )
                            repository.saveRingtone(ringtone)
                            savedRingtones.clear()
                            savedRingtones.addAll(repository.load())
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Сохранить рингтон")
                    }

                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text("Установить как ringtone")
                    }
                }
            }

            Text("Сохранённые варианты", fontWeight = FontWeight.SemiBold)

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(savedRingtones) { ringtone ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(ringtone.title, fontWeight = FontWeight.Medium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = buildString {
                                    append("Повтор: ${if (ringtone.loop) "Да" else "Нет"} • ")
                                    append("Вибрация: ${if (ringtone.vibrate) "Да" else "Нет"}")
                                },
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }
        }
    }
}
