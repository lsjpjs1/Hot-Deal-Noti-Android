package com.hotdealnoti.data.remote

import com.hotdealnoti.common.paging.Page
import com.hotdealnoti.common.paging.PageRequest
import com.hotdealnoti.notification.dto.NotificationDto
import retrofit2.Response
import retrofit2.http.*

interface NotificationService {


    @GET("/notifications")
    suspend fun getNotifications(
        @QueryMap pageRequest: PageRequest
    ): Response<Page<NotificationDto.Companion.NotificationResponseDto>>


}