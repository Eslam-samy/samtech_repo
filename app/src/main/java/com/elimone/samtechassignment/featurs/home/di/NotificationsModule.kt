package com.elimone.samtechassignment.featurs.home.di

import com.elimone.samtechassignment.featurs.home.data.data_source.NotificationsDao
import com.elimone.samtechassignment.featurs.home.data.paging.NotificationsLocalPagingSource
import com.elimone.samtechassignment.featurs.home.data.repository.NotificationsRepositoryImpl
import com.elimone.samtechassignment.featurs.home.domain.repository.NotificationsRepository
import com.elimone.samtechassignment.featurs.home.domain.usecases.GetNotificationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object NotificationsModule {

    @Provides
    @ViewModelScoped
    fun provideNotificationsRepository(
        localPagingSource: NotificationsLocalPagingSource,
        deo: NotificationsDao,
    ): NotificationsRepository =
        NotificationsRepositoryImpl(localPagingSource,deo)


    @Provides
    @ViewModelScoped
    fun bindNotificationsPagingLocal(
        deo: NotificationsDao,
    ): NotificationsLocalPagingSource {
        return NotificationsLocalPagingSource(deo)
    }


    @Provides
    @ViewModelScoped
    fun provideUseCase(repository: NotificationsRepository): GetNotificationUseCase =
        GetNotificationUseCase(repository)

}