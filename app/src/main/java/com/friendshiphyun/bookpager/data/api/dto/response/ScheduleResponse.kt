package com.friendshiphyun.bookpager.data.api.dto.response

data class ScheduleResponse(
    val status: String,
    val message: String,
    val hour: Int,
    val minute: Int
)
