package com.friendshiphyun.bookpager.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendshiphyun.bookpager.data.repository.BookFrameRepository
import com.friendshiphyun.bookpager.domain.model.Schedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime

class ScheduleViewModel(
    private val repository: BookFrameRepository = BookFrameRepository()
) : ViewModel() {

    private val _schedules = MutableStateFlow<List<Schedule>>(emptyList())
    val schedules: StateFlow<List<Schedule>> = _schedules.asStateFlow()

    private val _message = MutableStateFlow<Pair<String, Boolean>?>(null)
    val message: StateFlow<Pair<String, Boolean>?> = _message.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadSchedules() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getSchedules()
                .onSuccess { schedules ->
                    _schedules.value = schedules.sortedBy { it.time }
                }
                .onFailure { error ->
                    _message.value = (error.message ?: "일정 불러오기 실패") to false
                }
            _isLoading.value = false
        }
    }

    fun addSchedule(time: LocalTime, isEnabled: Boolean = true) {
        // 중복 체크
        if (_schedules.value.any { it.time == time }) {
            _message.value = "이미 같은 시간에 일정이 존재합니다" to false
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            repository.addSchedule(time.hour, time.minute)
                .onSuccess { message ->
                    loadSchedules()
                    _message.value = "일정이 추가되었습니다" to true
                }
                .onFailure { error ->
                    _message.value = (error.message ?: "일정 추가 실패") to false
                    _isLoading.value = false
                }
        }
    }

    fun clearMessage() {
        _message.value = null
    }
}
