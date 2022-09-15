package com.hotdealnoti.data.repository

import com.cmc.atracker.model.RetrofitClient
import com.hotdealnoti.BuildConfig
import com.hotdealnoti.data.remote.LoginService
import com.hotdealnoti.login.dto.LoginDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response

class AuthTokenRepository {

    companion object{
        val instance: AuthTokenRepository = AuthTokenRepository()
    }
    private val loginService: LoginService = RetrofitClient.getClient(BuildConfig.BASE_URL).create(LoginService::class.java)

    suspend fun kakaoLogin(loginRequest: LoginDto.Companion.KakaoLoginRequest): Response<LoginDto.Companion.LoginResponse> {
        return CoroutineScope(Dispatchers.IO).async {
            loginService.kakaoLogin(accessToken = loginRequest.accessToken)
        }.await()
    }
}