package com.hotdealnoti.data.remote

import com.hotdealnoti.common.paging.Page
import com.hotdealnoti.common.paging.PageRequest
import com.hotdealnoti.notification.dto.NotificationDto
import retrofit2.Response
import retrofit2.http.*

interface HotDealService {


    @GET("/hot-deals/hot-deal/{hotDealId}")
    suspend fun getHotDealById(
        @Path("hotDealId") hotDealId: Int
    ): Response<NotificationDto.Companion.HotDealPreview>


}