package com.elimone.samtechassignment.featurs.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.elimone.samtechassignment.featurs.home.domain.usecases.GetNotificationUseCase
import com.elimone.samtechassignment.featurs.home.domain.usecases.RemoveNotificationUseCase
import com.elimone.samtechassignment.featurs.home.presentation.mappers.toUiModel
import com.elimone.samtechassignment.featurs.home.presentation.model.NotificationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotificationUseCase: GetNotificationUseCase,
    private val removeNotificationUseCase: RemoveNotificationUseCase
) : ViewModel() {


    private val _servicesFlow =
        MutableStateFlow<PagingData<NotificationUiModel>>(PagingData.empty())
    val notifications: StateFlow<PagingData<NotificationUiModel>> = _servicesFlow

    init {
        getNotifications()
    }

    fun getNotifications() {
        viewModelScope.launch {
            val res = getNotificationUseCase()
                .cachedIn(viewModelScope)
            _servicesFlow.emit(res.first().map { it.toUiModel() })
        }
    }

//    fun deleteItem(position: Int) {
//        viewModelScope.launch {
//            // Access the latest PagingData snapshot
//            val snapshot = _servicesFlow.value.snapshot()
//            if (position in 0 until snapshot.size) {
//                val itemToDelete = snapshot[position]
//                removeNotificationUseCase(itemToDelete)
//            }
//        }
//    }







}