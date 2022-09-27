package com.hotdealnoti.notification.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotdealnoti.common.paging.PageRequest
import com.hotdealnoti.data.repository.HotDealRepository
import com.hotdealnoti.data.repository.KeywordRepository
import com.hotdealnoti.data.repository.NotificationRepository
import com.hotdealnoti.notification.dto.NotificationDto
import kotlinx.coroutines.launch

class ShowHotDealViewModel : ViewModel(){

    private val _toastMessage= MutableLiveData<String>()
    val toastMessage:LiveData<String> = _toastMessage


    private val _hotDealLink= MutableLiveData<String>()
    val hotDealLink:LiveData<String> = _hotDealLink






    fun getHotDealLink(hotDealId: Int){
        viewModelScope.launch {

            val response = HotDealRepository.instance.getHotDealById(hotDealId)
            if (response.isSuccessful ){

                _hotDealLink.value = response.body()?.link?:return@launch


            }else{
                _toastMessage.value = "오류 발생"//수정예정
            }
        }
    }

}