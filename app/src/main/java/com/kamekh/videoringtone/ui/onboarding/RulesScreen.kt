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
fun RulesScreen(onAccept: () -> Unit) {
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
            text = "Правила использования",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "- Уважайте других пользователей.\n- Запрещены оскорбления, угрозы, дискриминация и конфликты.\n- Запрещены насилие, пропаганда, травля и нарушение прав других.\n- Публичный режим доступен только по вашему выбору.\n- Личный режим остаётся приватным по умолчанию.\n- Нарушения могут привести к предупреждению, скрытию контента, блокировке или удалению.\n- Публичный контент доступен только после согласия и возраста 18+."
        )

        Button(
            onClick = onAccept,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Я прочитал и согласен")
        }
    }
}
