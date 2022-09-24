package com.hotdealnoti.notification.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotdealnoti.common.BooleanWrapper
import com.hotdealnoti.data.repository.KeywordRepository
import com.hotdealnoti.notification.dto.NotificationDto
import kotlinx.coroutines.launch

class AddNotificationViewModel : ViewModel(){
    var keywordBody= MutableLiveData<String>()

    private val _toastMessage= MutableLiveData<String>()
    val toastMessage:LiveData<String> = _toastMessage

    private val _notificationKeywords= MutableLiveData<List<NotificationDto.Companion.NotificationKeyword>>()
    val notificationKeywords:LiveData<List<NotificationDto.Companion.NotificationKeyword>> = _notificationKeywords

    private val _deleteKeywordSuccess= MutableLiveData<BooleanWrapper>()
    val deleteKeywordSuccess:LiveData<BooleanWrapper> = _deleteKeywordSuccess

    init {
        getNotificationKeywords()
    }



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
                getNotificationKeywords()

            }else{
                _toastMessage.value = "오류 발생"//수정예정
            }
        }
    }

    fun getNotificationKeywords(){
        viewModelScope.launch {

            val response = KeywordRepository.instance.getKeywords()
            if (response.isSuccessful ){

                val getKeywordsResponse = response.body()?:return@launch
                _notificationKeywords.value=getKeywordsResponse.keywords

            }else{
                _toastMessage.value = "오류 발생"//수정예정
            }
        }
    }

    fun deleteKeyword(keywordId:Int){
        viewModelScope.launch {

            val response = KeywordRepository.instance.deleteKeyword(keywordId)
            if (response.isSuccessful ){
                _deleteKeywordSuccess.value = BooleanWrapper(true)


            }else{
                _toastMessage.value = "오류 발생"//수정예정
            }
        }
    }
}