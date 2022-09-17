package com.hotdealnoti.notification.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.atracker.model.RetrofitClient
import com.hotdealnoti.data.local.App
import com.hotdealnoti.data.repository.AuthTokenRepository
import com.hotdealnoti.data.repository.KeywordRepository
import com.hotdealnoti.login.dto.LoginDto
import com.hotdealnoti.notification.dto.NotificationDto
import kotlinx.coroutines.launch

class AddNotificationViewModel : ViewModel(){
    var keywordBody= MutableLiveData<String>()

    private val _toastMessage= MutableLiveData<String>()
    val toastMessage:LiveData<String> = _toastMessage



    fun postKeyword(){
        viewModelScope.launch {

            if (keywordBody.value==null){
                _toastMessage.value = "키워드를 입력해주세요!"//수정예정
                return@launch
            }
            if (keywordBody.value.equals("")){
                _toastMessage.value = "키워드를 입력해주세요!"//수정예정
                return@launch
            }

            val postKeywordRequest = NotificationDto.Companion.PostKeywordRequest(keywordBody.value ?: return@launch)
            val response = KeywordRepository.instance.postKeyword(postKeywordRequest)
            if (response.isSuccessful ){

                _toastMessage.value = "등록 완료"//수정예정
                keywordBody.value = ""

            }else{
                _toastMessage.value = "오류 발생"//수정예정
            }
        }
    }
}