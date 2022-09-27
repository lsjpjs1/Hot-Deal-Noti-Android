package com.hotdealnoti.data.repository

import com.cmc.atracker.model.RetrofitClient
import com.hotdealnoti.BuildConfig
import com.hotdealnoti.common.paging.Page
import com.hotdealnoti.common.paging.PageRequest
import com.hotdealnoti.data.remote.HotDealService
import com.hotdealnoti.data.remote.KeywordService
import com.hotdealnoti.data.remote.LoginService
import com.hotdealnoti.data.remote.NotificationService
import com.hotdealnoti.login.dto.LoginDto
import com.hotdealnoti.notification.dto.NotificationDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response

class HotDealRepository {

    companion object {
        val instance: HotDealRepository = HotDealRepository()
    }

    private val hotDealService: HotDealService =
        RetrofitClient.getClient(BuildConfig.BASE_URL).create(HotDealService::class.java)


    suspend fun getHotDealById(hotDealId: Int): Response<NotificationDto.Companion.HotDealPreview> {
        return CoroutineScope(Dispatchers.IO).async {
            hotDealService.getHotDealById(hotDealId=hotDealId)
        }.await()
    }

}