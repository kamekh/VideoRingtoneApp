package com.kamekh.videoringtone.ui.onboarding

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            MaterialTheme.colorScheme.background
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "VideoRingtone",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Создавайте, сохраняйте и устанавливайте видео-рингтоны безопасно и удобно.",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )

        Button(
            onClick = onStart,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Начать")
        }
    }
}
