package com.hotdealnoti.data.repository

import com.cmc.atracker.model.RetrofitClient
import com.hotdealnoti.BuildConfig
import com.hotdealnoti.common.paging.Page
import com.hotdealnoti.common.paging.PageRequest
import com.hotdealnoti.data.remote.KeywordService
import com.hotdealnoti.data.remote.LoginService
import com.hotdealnoti.data.remote.NotificationService
import com.hotdealnoti.login.dto.LoginDto
import com.hotdealnoti.notification.dto.NotificationDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response

class NotificationRepository {

    companion object {
        val instance: NotificationRepository = NotificationRepository()
    }

    private val notificationService: NotificationService =
        RetrofitClient.getClient(BuildConfig.BASE_URL).create(NotificationService::class.java)


    suspend fun getNotifications(pageRequest: PageRequest): Response<Page<NotificationDto.Companion.NotificationResponseDto>> {
        return CoroutineScope(Dispatchers.IO).async {
            notificationService.getNotifications(pageRequest)
        }.await()
    }

}