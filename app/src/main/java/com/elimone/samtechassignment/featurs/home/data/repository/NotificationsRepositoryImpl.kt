package com.elimone.samtechassignment.featurs.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.elimone.samtechassignment.featurs.home.data.data_source.NotificationsDao
import com.elimone.samtechassignment.featurs.home.data.model.NotificationLocalModel
import com.elimone.samtechassignment.featurs.home.data.paging.NotificationsLocalPagingSource
import com.elimone.samtechassignment.featurs.home.domain.repository.NotificationsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotificationsRepositoryImpl @Inject constructor(
    private val notificationsLocalPagingSource: NotificationsLocalPagingSource,
    private val notificationsDao: NotificationsDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    NotificationsRepository {

    override suspend fun getCachedNotifications(): Pager<Int, NotificationLocalModel> =
        withContext(dispatcher) {
            return@withContext Pager(config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                notificationsLocalPagingSource
            })

        }

    override suspend fun removeNotifications(id: String) =
        withContext(dispatcher)
        {
            notificationsDao.deleteNotificationById(id)

        }

    override suspend fun updateNotification(id: String, title: String?, body: String?) {
        withContext(dispatcher)
        {
            notificationsDao.updateNotification(id, title, body)

        }
    }
}