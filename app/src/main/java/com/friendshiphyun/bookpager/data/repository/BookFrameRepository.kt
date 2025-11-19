package com.friendshiphyun.bookpager.data.repository

import com.friendshiphyun.bookpager.data.api.ApiClient
import com.friendshiphyun.bookpager.data.api.BookFrameApi
import com.friendshiphyun.bookpager.data.api.dto.request.MotorControlRequest
import com.friendshiphyun.bookpager.data.api.dto.request.ScheduleRequest
import com.friendshiphyun.bookpager.data.api.dto.response.ScheduleDto
import com.friendshiphyun.bookpager.data.api.dto.response.StatusResponse
import com.friendshiphyun.bookpager.domain.model.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalTime

/**
 * BookFrame Repository
 * API 호출, 결과 처리
 */
class BookFrameRepository(
    private val api: BookFrameApi = ApiClient.api
) {

    suspend fun getStatus(): Result<StatusResponse> = withContext(Dispatchers.IO) {
        try {
            val response = api.getStatus()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("응답 데이터가 없습니다"))
                }
            } else {
                Result.failure(Exception("상태 확인 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun turnPage(): Result<String> = withContext(Dispatchers.IO) {
        try {
            val response = api.turnPage()
            if (response.isSuccessful) {
                val body = response.body()
                Result.success(body?.message ?: "페이지가 넘어갔습니다")
            } else {
                Result.failure(Exception("오류 발생: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun controlMotor(motor: Int, forward: Boolean): Result<String> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.controlMotor(MotorControlRequest(motor, forward))
                if (response.isSuccessful) {
                    val direction = if (forward) "정방향" else "반대방향"
                    Result.success("모터 ${motor}이(가) ${direction}으로 회전했습니다")
                } else {
                    Result.failure(Exception("모터 제어 실패: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    /**
     * ESP32로부터 일정 목록 조회
     */
    suspend fun getSchedules(): Result<List<Schedule>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getStatus()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val schedules = body.schedules.map { it.toSchedule() }
                    Result.success(schedules)
                } else {
                    Result.failure(Exception("응답 데이터가 없습니다"))
                }
            } else {
                Result.failure(Exception("일정 조회 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * ESP32에 일정 추가
     */
    suspend fun addSchedule(hour: Int, minute: Int): Result<String> = withContext(Dispatchers.IO) {
        try {
            val response = api.setSchedule(ScheduleRequest(hour, minute))
            if (response.isSuccessful) {
                val body = response.body()
                Result.success(body?.message ?: "일정이 추가되었습니다")
            } else {
                Result.failure(Exception("일정 추가 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 일정 업데이트
     */
    suspend fun updateSchedule(hour: Int, minute: Int, enable: Boolean): Result<String> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.updateSchedule(ScheduleRequest(hour, minute, enable))
                if (response.isSuccessful) {
                    val body = response.body()
                    Result.success(body?.message ?: "일정이 수정 되었습니다")
                } else {
                    Result.failure(Exception("일정 추가 실패: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    /**
     * 일정 삭제
     */
    suspend fun deleteSchedule(hour: Int, minute: Int): Result<String> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.deleteSchedule(ScheduleRequest(hour, minute))
                if (response.isSuccessful) {
                    val body = response.body()
                    Result.success(body?.message ?: "일정이 삭제 되었습니다")
                } else {
                    Result.failure(Exception("일정 추가 실패: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    /**
     * ScheduleDto를 Schedule 도메인 모델로 변환
     */
    private fun ScheduleDto.toSchedule(): Schedule {
        return Schedule(
            time = LocalTime.of(hour, minute),
            isEnabled = enabled
        )
    }
}
