package com.elimone.samtechassignment.featurs.home.domain.usecases

import com.elimone.samtechassignment.featurs.home.domain.repository.NotificationsRepository
import javax.inject.Inject

class UpdateNotificationUseCase @Inject constructor(
    private val repository: NotificationsRepository,
) {
    suspend operator fun invoke(id: String, title: String?, body: String?) {
        repository.updateNotification(id,title,body)
    }
}