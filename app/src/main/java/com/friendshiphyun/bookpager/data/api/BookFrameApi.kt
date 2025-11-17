package com.friendshiphyun.bookpager.data.api

import com.friendshiphyun.bookpager.data.api.dto.response.StatusResponse
import retrofit2.Response
import retrofit2.http.GET

interface BookFrameApi {
    /**
     * ESP32 상태 확인
     */
    @GET("status")
    suspend fun getStatus(): Response<StatusResponse>
}
