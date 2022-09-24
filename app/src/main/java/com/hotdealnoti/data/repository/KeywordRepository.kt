package com.hotdealnoti.data.repository

import com.cmc.atracker.model.RetrofitClient
import com.hotdealnoti.BuildConfig
import com.hotdealnoti.data.remote.KeywordService
import com.hotdealnoti.data.remote.LoginService
import com.hotdealnoti.login.dto.LoginDto
import com.hotdealnoti.notification.dto.NotificationDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response

class KeywordRepository {

    companion object{
        val instance: KeywordRepository = KeywordRepository()
    }

    private val keywordService: KeywordService = RetrofitClient.getClient(BuildConfig.BASE_URL).create(KeywordService::class.java)

    suspend fun postKeyword(postKeywordRequest: NotificationDto.Companion.PostKeywordRequest): Response<Void> {
        return CoroutineScope(Dispatchers.IO).async {
            keywordService.postKeyword(postKeywordRequest)
        }.await()
    }

    suspend fun getKeywords(): Response<NotificationDto.Companion.GetKeywordsResponse> {
        return CoroutineScope(Dispatchers.IO).async {
            keywordService.getKeywords()
        }.await()
    }

    suspend fun deleteKeyword(notificationKeywordId: Int): Response<Void> {
        return CoroutineScope(Dispatchers.IO).async {
            keywordService.deleteKeyword(notificationKeywordId)
        }.await()
    }
}