package com.friendshiphyun.bookpager.data.repository

import com.friendshiphyun.bookpager.data.api.ApiClient
import com.friendshiphyun.bookpager.data.api.BookFrameApi
import com.friendshiphyun.bookpager.data.api.dto.request.MotorControlRequest
import com.friendshiphyun.bookpager.data.api.dto.response.StatusResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

    suspend fun controlMotor(motor: Int, forward: Boolean): Result<String> = withContext(Dispatchers.IO) {
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
}
