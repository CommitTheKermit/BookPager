package com.friendshiphyun.bookpager.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendshiphyun.bookpager.data.repository.BookFrameRepository
import com.friendshiphyun.bookpager.presentation.main.model.MotorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MotorControlViewModel(
    private val repository: BookFrameRepository = BookFrameRepository()
) : ViewModel() {

    private val _motorState = MutableStateFlow(MotorState())
    val motorState: StateFlow<MotorState> = _motorState.asStateFlow()

    fun rotateMotorForward(motor: Int) {
        viewModelScope.launch {
            _motorState.update {
                it.copy(
                    isLoading = true,
                    message = "",
                )
            }

            repository.controlMotor(motor, true)
                .onSuccess { message ->
                    _motorState.update {
                        it.copy(
                            isLoading = false,
                            message = message,
                        )
                    }
                }
                .onFailure { error ->
                    _motorState.update {
                        it.copy(
                            isLoading = false,
                            message = "모터 제어 실패: ${error.message}",
                        )
                    }
                }
        }
    }

    fun rotateMotorBackward(motor: Int) {
        viewModelScope.launch {
            _motorState.update {
                it.copy(
                    isLoading = true,
                    message = "",
                )
            }

            repository.controlMotor(motor, false)
                .onSuccess { message ->
                    _motorState.update {
                        it.copy(
                            isLoading = false,
                            message = message,
                        )
                    }
                }
                .onFailure { error ->
                    _motorState.update {
                        it.copy(
                            isLoading = false,
                            message = "모터 제어 실패: ${error.message}",
                        )
                    }
                }
        }
    }
}
