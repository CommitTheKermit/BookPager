package com.friendshiphyun.bookpager.data.api

import com.friendshiphyun.bookpager.data.api.dto.request.MotorControlRequest
import com.friendshiphyun.bookpager.data.api.dto.request.ScheduleRequest
import com.friendshiphyun.bookpager.data.api.dto.response.MotorControlResponse
import com.friendshiphyun.bookpager.data.api.dto.response.ScheduleResponse
import com.friendshiphyun.bookpager.data.api.dto.response.StatusResponse
import com.friendshiphyun.bookpager.data.api.dto.response.TurnResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookFrameApi {
    /**
     * ESP32 상태 확인
     */
    @GET("status")
    suspend fun getStatus(): Response<StatusResponse>

    /**
     * 페이지 넘기기
     */
    @POST("turn")
    suspend fun turnPage(): Response<TurnResponse>

    /**
     * 모터 제어
     */
    @POST("motor")
    suspend fun controlMotor(@Body motorControl: MotorControlRequest): Response<MotorControlResponse>

    /**
     * 일정 설정
     */
    @POST("setSchedule")
    suspend fun setSchedule(@Body request: ScheduleRequest): Response<ScheduleResponse>
}
