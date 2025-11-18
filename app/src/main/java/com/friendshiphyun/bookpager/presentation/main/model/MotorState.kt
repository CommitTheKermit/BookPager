package com.friendshiphyun.bookpager.presentation.main.model

/**
 * 모터 제어 상태를 나타내는 데이터 클래스
 */
data class MotorState(
    val isLoading: Boolean = false,
    val message: String = "",
)
