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


    private var notificationPageRequest = PageRequest(0,10)




    fun getNotifications(isMore:Boolean = false){
        viewModelScope.launch {

            val response = NotificationRepository.instance.getNotifications(notificationPageRequest)
            if (response.isSuccessful ){


                if (isMore){
                    val newNotifications = response.body()?.content ?: return@launch
                    val copyList = _notifications.value?.toMutableList() ?: return@launch
                    copyList.addAll(newNotifications)
                    _notifications.value = copyList
                }else{
                    _notifications.value = response.body()?.content?:return@launch
                }
                notificationPageRequest = PageRequest((response.body()?.pageable?.pageNumber?:return@launch)+1,10)
                if (response.body()?.numberOfElements==0){
                    _toastMessage.value = "더 이상 알림이 없습니다."//수정예정
                }

            }else{
                _toastMessage.value = "오류 발생"//수정예정
            }
        }
    }

    fun refreshNotification() {
        viewModelScope.launch {
            notificationPageRequest = PageRequest(0,10)
            getNotifications()
        }
    }

}