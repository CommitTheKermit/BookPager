package com.friendshiphyun.bookpager.data.api.dto.response

data class SetScheduleResponse(
    val status: String,
    val message: String,
    val hour: Int,
    val minute: Int
)
