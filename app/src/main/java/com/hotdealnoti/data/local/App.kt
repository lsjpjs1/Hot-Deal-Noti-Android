package com.hotdealnoti.data.local

import android.app.Application
import com.hotdealnoti.BuildConfig
import com.kakao.sdk.common.KakaoSdk

class App: Application() {
    companion object{
        lateinit var prefs : MySharedPreferences
    }

    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
        super.onCreate()
    }
}