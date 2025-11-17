package com.friendshiphyun.bookpager.presentation.main.model

import com.friendshiphyun.bookpager.data.api.dto.response.StatusResponse

data class FrameState(
//    하드웨어 동작 끝났는 지 여부
    val isLoading: Boolean = false,
    val message: String = "",
//    ESP32와 연결되어 있는 지 여부
    val isConnected: Boolean = false,
    val esp32Status: StatusResponse? = null,
)