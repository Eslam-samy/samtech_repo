package com.elimone.samtechassignment.di

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elimone.samtechassignment.featurs.home.data.data_source.NotificationsDao
import com.elimone.samtechassignment.featurs.home.data.model.NotificationLocalModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDBModule {

    @Provides
    @Singleton
    fun provideRoomDB(
        applicationContext: Application
    ): NotificationsDao {
        return Room.databaseBuilder(
            (applicationContext as Context),
            AppDatabase::class.java, "Notifications-db"
        ).build().notificationsDio()
    }

}

@Database(entities = [NotificationLocalModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationsDio(): NotificationsDao
}