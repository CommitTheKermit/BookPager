package com.friendshiphyun.bookpager.data.api.dto.response

/**
 * ESP32 상태 응답
 */
data class StatusResponse(
    val connected: Boolean,
    val wifi: String,
    val timerActive: Boolean = false,
    val timerInterval: Int = 0
)
