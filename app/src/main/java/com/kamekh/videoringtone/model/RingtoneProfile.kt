package com.kamekh.videoringtone.model

import java.util.UUID

enum class VideoFitMode(val label: String) {
    AUTO_FIT("Авто подгон"),
    FILL("Заполнить экран"),
    CENTER_CROP("Обрезать по центру"),
    FIT_WITH_BARS("Сохранить пропорции")
}

enum class VideoCropMode(val label: String) {
    FULL("Полный кадр"),
    PHONE_CALL("Входящий вызов"),
    PORTRAIT("Портрет"),
    WIDE("Широкий")
}

data class RingtoneProfile(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val videoUri: String? = null,
    val musicUri: String? = null,
    val loop: Boolean = true,
    val vibrate: Boolean = true,
    val fitMode: VideoFitMode = VideoFitMode.AUTO_FIT,
    val cropMode: VideoCropMode = VideoCropMode.PHONE_CALL,
    val zoom: Float = 1.0f,
    val xOffset: Float = 0f,
    val yOffset: Float = 0f,
    val isPrivate: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
