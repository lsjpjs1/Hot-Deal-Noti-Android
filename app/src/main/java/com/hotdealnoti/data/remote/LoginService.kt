package com.hotdealnoti.data.remote

import com.hotdealnoti.login.dto.LoginDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {

    @GET("/oauth/callback/kakao")
    suspend fun kakaoLogin(
        @Query("accessToken") accessToken: String
    ): Response<LoginDto.Companion.LoginResponse>
}