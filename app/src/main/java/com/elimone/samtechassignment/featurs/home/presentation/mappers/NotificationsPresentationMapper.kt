package com.elimone.samtechassignment.featurs.home.presentation.mappers

import com.elimone.samtechassignment.featurs.home.data.model.NotificationLocalModel
import com.elimone.samtechassignment.featurs.home.domain.model.NotificationDomainModel
import com.elimone.samtechassignment.featurs.home.presentation.model.NotificationUiModel


fun NotificationDomainModel.toUiModel() = NotificationUiModel(
    title, id, body, time
)