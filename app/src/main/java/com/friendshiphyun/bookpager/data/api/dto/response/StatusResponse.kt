package com.friendshiphyun.bookpager.data.api.dto.response

/**
 * ESP32 상태 응답
 */
data class StatusResponse(
    val connected: Boolean,
    val wifi: String,
    val hour: Int = 0,
    val minute: Int = 0,
    val schedules: List<ScheduleDto> = emptyList()
)

data class ScheduleDto(
    val index: Int,
    val hour: Int,
    val minute: Int,
    val enabled: Boolean
)
