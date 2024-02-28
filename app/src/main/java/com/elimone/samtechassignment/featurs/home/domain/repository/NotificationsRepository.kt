package com.elimone.samtechassignment.featurs.home.domain.repository

import androidx.paging.Pager
import com.elimone.samtechassignment.featurs.home.data.model.NotificationLocalModel

interface NotificationsRepository {
    suspend fun getCachedNotifications(
    ): Pager<Int, NotificationLocalModel>

    suspend fun removeNotifications(
        id: String
    )

    suspend fun updateNotification(
        id: String, title: String?, body: String?
    )
}