package com.elimone.samtechassignment.featurs.home.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elimone.samtechassignment.featurs.home.data.model.NotificationLocalModel

@Dao
interface NotificationsDao {

    @Query("SELECT * FROM notification ORDER BY time DESC LIMIT :limit OFFSET :offset ")
    suspend fun getNotifications(
        limit: Int,
        offset: Int,
    ): List<NotificationLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(movieLocal: NotificationLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotifications(movies: List<NotificationLocalModel>)

    @Query("DELETE FROM notification")
    suspend fun clearNotifications()

    @Query("DELETE FROM notification WHERE id = :notificationId")
    suspend fun deleteNotificationById(notificationId: String)

    @Query("UPDATE notification SET title = :newTitle, body = :newBody WHERE id = :notificationId")
    suspend fun updateNotification(notificationId: String, newTitle: String?, newBody: String?)


}
