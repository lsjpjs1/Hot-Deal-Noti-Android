package com.hotdealnoti.data.remote

import com.hotdealnoti.notification.dto.NotificationDto
import retrofit2.Response
import retrofit2.http.*

interface KeywordService {

    @POST("/notification-keywords")
    suspend fun postKeyword(
        @Body postKeywordRequest: NotificationDto.Companion.PostKeywordRequest
    ): Response<Void>

    @GET("/notification-keywords")
    suspend fun getKeywords(

    ): Response<NotificationDto.Companion.GetKeywordsResponse>

    @DELETE("/notification-keywords/{keywordNotificationId}")
    suspend fun deleteKeyword(
        @Path("keywordNotificationId") keywordNotificationId:Int
    ): Response<Void>
}