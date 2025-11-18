package com.friendshiphyun.bookpager.data.api.dto.request

/**
 * 모터 제어 요청
 */
data class MotorControlRequest(
    val motor: Int,      // 1, 2, 3
    val forward: Boolean // true: 정방향, false: 반대방향
)
