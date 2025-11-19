package com.friendshiphyun.bookpager.data.api.dto.request

data class ScheduleRequest(
    val hour: Int,
    val minute: Int,
    val enable: Boolean = true
)
