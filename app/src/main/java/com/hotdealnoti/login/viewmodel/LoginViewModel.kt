package com.hotdealnoti.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotdealnoti.data.local.App
import com.hotdealnoti.data.repository.AuthTokenRepository
import com.hotdealnoti.login.dto.LoginDto
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){
    private val _loginSuccess= MutableLiveData<Boolean>()
    val loginSuccess:LiveData<Boolean> = _loginSuccess

    private val _toastMessage= MutableLiveData<String>()
    val toastMessage:LiveData<String> = _toastMessage



    fun kakaoLogin(accessToken: String){
        Log.d("accessToken",accessToken)
        viewModelScope.launch {

            Log.d("accessToken",accessToken)
            val kakaoLoginRequest = LoginDto.Companion.KakaoLoginRequest(accessToken = accessToken)
            val response = AuthTokenRepository.instance.kakaoLogin(kakaoLoginRequest)
            Log.d("successful",response.isSuccessful.toString())
            if (response.isSuccessful ){

                App.prefs.setValue("AUTH_TOKEN",response.body()?.token)
                _loginSuccess.value=true
            }else{
                _toastMessage.value = "로그인 오류"
            }
        }
    }
}