package com.kamekh.videoringtone.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PreferenceChip(
    val label: String,
    val selected: Boolean = false
)

@Composable
fun UserPreferencesScreen(onSave: () -> Unit) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.18f),
            MaterialTheme.colorScheme.background
        )
    )

    var selectedGenres by remember {
        mutableStateOf(listOf("Chill", "Romantic", "Electronic"))
    }

    var selectedMoods by remember {
        mutableStateOf(listOf("Calm", "Dreamy"))
    }

    val genres = listOf("Chill", "Romantic", "Electronic", "Jazz", "Lo-fi", "Pop", "Rock", "Ambient")
    val moods = listOf("Calm", "Romantic", "Energetic", "Cozy", "Dreamy", "Warm", "Soft", "Bright")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Ваши предпочтения",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Выберите жанры и настроение, чтобы получать более подходящие рекомендации. Это необязательно и остаётся приватным.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )

        Text("Жанры", fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genres.forEach { genre ->
                val selected = selectedGenres.contains(genre)
                OutlinedButton(
                    onClick = {
                        selectedGenres = if (selected) {
                            selectedGenres - genre
                        } else {
                            selectedGenres + genre
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(genre)
                }
            }
        }

        Text("Настроение", fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            moods.forEach { mood ->
                val selected = selectedMoods.contains(mood)
                OutlinedButton(
                    onClick = {
                        selectedMoods = if (selected) {
                            selectedMoods - mood
                        } else {
                            selectedMoods + mood
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(mood)
                }
            }
        }

        Button(
            onClick = onSave,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Сохранить")
        }
    }
}
