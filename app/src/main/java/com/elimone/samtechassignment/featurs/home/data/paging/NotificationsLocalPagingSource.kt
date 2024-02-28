package com.elimone.samtechassignment.featurs.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elimone.samtechassignment.featurs.home.data.data_source.NotificationsDao
import com.elimone.samtechassignment.featurs.home.data.model.NotificationLocalModel
import java.io.IOException

class NotificationsLocalPagingSource(
    private val servicesDao: NotificationsDao,
    private val firstPage: Int = 1,

    ) : PagingSource<Int, NotificationLocalModel>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NotificationLocalModel> {
        return try {
            val currentPage = params.key ?: firstPage

            val notifications = servicesDao.getNotifications(
                params.loadSize,
                (currentPage - 1) * params.loadSize,
            )

            val nextPage: Int? =
                if (notifications.isEmpty()) null else currentPage.plus(1)

            if (notifications.isEmpty()) {
                throw IOException()
            }

            LoadResult.Page(
                data = notifications,
                prevKey = if (currentPage == 1) null else currentPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NotificationLocalModel>): Int {
        return 0
    }

}