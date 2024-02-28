package com.elimone.samtechassignment.featurs.single_notifications.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elimone.samtechassignment.featurs.home.domain.usecases.GetNotificationUseCase
import com.elimone.samtechassignment.featurs.home.domain.usecases.RemoveNotificationUseCase
import com.elimone.samtechassignment.featurs.home.domain.usecases.UpdateNotificationUseCase
import com.elimone.samtechassignment.featurs.home.presentation.model.NotificationUiModel
import com.elimone.samtechassignment.featurs.single_notifications.presentation.contract.SingleNotificationContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class SingleNotificationViewModel @Inject constructor(
    private val removeNotificationUseCase: RemoveNotificationUseCase,
    private val updateNotificationUseCase: UpdateNotificationUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<SingleNotificationContract> = MutableStateFlow(
        SingleNotificationContract()
    )
    val uiState: StateFlow<SingleNotificationContract> = _uiState

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = _uiState.value.copy(
            loading = false
        )
    }

    fun setInitialData(model: NotificationUiModel) {
        _uiState.value = _uiState.value.copy(
            title = MutableStateFlow(model.title),
            body = MutableStateFlow(model.body),
            id = model.id
        )
    }

    fun removeNotification() {
        viewModelScope.launch(errorHandler) {
            removeNotificationUseCase(_uiState.value.id!!)
            _uiState.value = _uiState.value.copy(
                confirmed = true
            )
        }
    }

    fun updateNotification(

    ) {
        viewModelScope.launch(errorHandler) {
            updateNotificationUseCase(
                _uiState.value.id!!,
                _uiState.value.title.value,
                _uiState.value.body.value
            )
            _uiState.value = _uiState.value.copy(
                confirmed = true
            )
        }
    }
}