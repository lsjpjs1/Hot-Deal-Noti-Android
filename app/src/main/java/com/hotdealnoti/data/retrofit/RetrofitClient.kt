package com.cmc.atracker.model

import com.google.gson.GsonBuilder
import com.hotdealnoti.BuildConfig
import com.hotdealnoti.data.local.App
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private lateinit var retrofit: Retrofit
    private lateinit var loggingInterceptor: HttpLoggingInterceptor
    private lateinit var addHeaderInterceptor: Interceptor
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(addHeaderInterceptor)
            .addNetworkInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", "Android")
                chain.proceed(requestBuilder.build())
            }.build()
    }

    fun getClient(baseUrl: String): Retrofit {
        if ( !this::retrofit.isInitialized) {
            loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            addHeaderInterceptor = object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val token = App.prefs.getValue("AUTH_TOKEN")?:""
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build()
                    return chain.proceed(newRequest)
                }

            }

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun updateAuthToken(authToken: String){
        loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        addHeaderInterceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + authToken)
                    .build()
                return chain.proceed(newRequest)
            }
        }

        val baseUrl = if(this::retrofit.isInitialized) retrofit.baseUrl().url().toString() else BuildConfig.BASE_URL
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
