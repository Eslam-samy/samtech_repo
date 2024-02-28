package com.elimone.samtechassignment.featurs.home.domain.usecases

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.map
import com.elimone.samtechassignment.featurs.home.data.mapper.toDomainModel
import com.elimone.samtechassignment.featurs.home.domain.model.NotificationDomainModel
import com.elimone.samtechassignment.featurs.home.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoveNotificationUseCase @Inject constructor(
    private val repository: NotificationsRepository,
) {
    suspend operator fun invoke(id: String) {
        repository.removeNotifications(id)
    }
}