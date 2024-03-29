package com.hotdealnoti.login.dto

import java.sql.Timestamp

class LoginDto {
    companion object {


        data class KakaoLoginRequest(
            val accessToken: String,
            val notificationToken: String?
        )

        data class LoginResponse(
            val token: String
        )


    }
}