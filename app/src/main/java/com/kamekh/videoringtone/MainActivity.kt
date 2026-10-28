package com.kamekh.videoringtone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.kamekh.videoringtone.data.RingtoneRepository
import com.kamekh.videoringtone.data.StoredRingtone
import com.kamekh.videoringtone.model.RingtoneProfile
import com.kamekh.videoringtone.ui.preview.PreviewScreen
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

    var selectedVideoName by remember { mutableStateOf("Пока ничего не выбрано") }
    var selectedMusicName by remember { mutableStateOf("Без музыки") }
    var loopEnabled by remember { mutableStateOf(true) }
    var vibrateEnabled by remember { mutableStateOf(true) }
    var showPreview by remember { mutableStateOf(false) }
    var currentProfile by remember {
        mutableStateOf(
            RingtoneProfile(
                title = "Мой рингтон",
                videoUri = null,
                musicUri = null,
                loop = true,
                vibrate = true
            )
        )
    }

    savedRingtones.clear()
    savedRingtones.addAll(repository.load())

    if (showPreview) {
        PreviewScreen(
            profile = currentProfile,
            onBack = { showPreview = false },
            onApply = { showPreview = false }
        )
        return
    }

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
                text = "Выберите видео, музыку и проверьте, как это будет выглядеть на входящем вызове.",
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
                        Button(onClick = { selectedVideoName = "sample-video.mp4" }) {
                            Text("Выбрать")
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Музыка")
                        Button(onClick = { selectedMusicName = "sample-audio.mp3" }) {
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
                            currentProfile = RingtoneProfile(
                                title = selectedVideoName.takeIf { it != "Пока ничего не выбрано" } ?: "Мой рингтон",
                                videoUri = if (selectedVideoName != "Пока ничего не выбрано") "video://selected" else null,
                                musicUri = if (selectedMusicName != "Без музыки") "audio://selected" else null,
                                loop = loopEnabled,
                                vibrate = vibrateEnabled
                            )
                            showPreview = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Предпросмотр")
                    }

                    Button(
                        onClick = {
                            val ringtone = StoredRingtone(
                                id = System.currentTimeMillis().toString(),
                                title = selectedVideoName.takeIf { it != "Пока ничего не выбрано" } ?: "Мой рингтон",
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
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("Сохранить рингтон")
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
