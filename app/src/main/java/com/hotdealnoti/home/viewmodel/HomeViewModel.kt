package com.hotdealnoti.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotdealnoti.common.paging.PageRequest
import com.hotdealnoti.data.repository.KeywordRepository
import com.hotdealnoti.data.repository.NotificationRepository
import com.hotdealnoti.notification.dto.NotificationDto
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(){

    private val _toastMessage= MutableLiveData<String>()
    val toastMessage:LiveData<String> = _toastMessage









}