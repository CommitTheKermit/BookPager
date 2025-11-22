package com.friendshiphyun.bookpager.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendshiphyun.bookpager.data.repository.BookFrameRepository
import com.friendshiphyun.bookpager.presentation.main.model.FrameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FrameViewModel(
    private val repository: BookFrameRepository = BookFrameRepository(),
    val scheduleViewModel: ScheduleViewModel = ScheduleViewModel()
) : ViewModel() {

    private val _uiState = MutableStateFlow(FrameState())
    val uiState: StateFlow<FrameState> = _uiState.asStateFlow()

    init {
        // 앱 시작 시 ESP32 상태 확인
        checkStatus()

    }

    fun checkStatus() {
        viewModelScope.launch {
            repository.getStatus()
                .onSuccess { status ->
                    _uiState.update {
                        it.copy(
                            isConnected = status.connected,
                            esp32Status = status,
                            message = "ESP32 연결됨: ${status.wifi}"
                        )
                    }
                    // 상태 확인 후 스케줄 로드
                    scheduleViewModel.loadSchedules()
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isConnected = false,
                            message = "연결 확인 실패: ${error.message}"
                        )
                    }
                }
        }
    }

    fun turnPage() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, message = "") }

            repository.turnPage()
                .onSuccess { message ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            message = message,
                            isConnected = true
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            message = "연결 실패: ${error.message}",
                            isConnected = false
                        )
                    }
                }
        }
    }
}