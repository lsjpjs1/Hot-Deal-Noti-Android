package com.hotdealnoti.notification.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotdealnoti.common.paging.PageRequest
import com.hotdealnoti.data.repository.KeywordRepository
import com.hotdealnoti.data.repository.NotificationRepository
import com.hotdealnoti.notification.dto.NotificationDto
import kotlinx.coroutines.launch

class NotificationViewModel : ViewModel(){

    private val _toastMessage= MutableLiveData<String>()
    val toastMessage:LiveData<String> = _toastMessage

    private val _notifications= MutableLiveData<List<NotificationDto.Companion.NotificationResponseDto>>()
    val notifications:LiveData<List<NotificationDto.Companion.NotificationResponseDto>> = _notifications






    fun getNotifications(){
        viewModelScope.launch {

            val response = NotificationRepository.instance.getNotifications(PageRequest(0,10))
            if (response.isSuccessful ){

                _notifications.value = response.body()?.content?:return@launch


            }else{
                _toastMessage.value = "오류 발생"//수정예정
            }
        }
    }

}