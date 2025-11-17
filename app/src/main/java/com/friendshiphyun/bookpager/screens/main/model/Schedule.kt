package com.friendshiphyun.bookpager.screens.main.model

import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class Schedule(
    val time: LocalTime = LocalTime.of(0, 0),
    val isEnabled: Boolean = true,
) {
    fun getDisplayTime(): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return time.format(formatter)
    }
}
