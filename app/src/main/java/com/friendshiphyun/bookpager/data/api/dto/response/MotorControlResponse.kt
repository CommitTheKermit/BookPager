package com.friendshiphyun.bookpager.data.api.dto.response

/**
 * 모터 제어 응답
 */
data class MotorControlResponse(
    val status: String,
    val motor: Int,
    val direction: String
)
