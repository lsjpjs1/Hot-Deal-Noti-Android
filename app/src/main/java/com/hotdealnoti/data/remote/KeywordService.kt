package com.hotdealnoti.data.remote

import com.hotdealnoti.login.dto.LoginDto
import com.hotdealnoti.notification.dto.NotificationDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface KeywordService {

    @POST("/notification-keywords")
    suspend fun postKeyword(
        @Body postKeywordRequest: NotificationDto.Companion.PostKeywordRequest
    ): Response<Void>
}