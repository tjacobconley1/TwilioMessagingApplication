package com.example.twiliosmstest.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("send_sms")
    suspend fun sendSms(
        @Body request: SmsRequest
    ): Response<SmsResponse>
}

data class SmsRequest(val to: String, val body: String)
data class SmsResponse(val status: String, val sid: String?)
