package com.elimone.samtechassignment.featurs.home.data.mapper

import com.elimone.samtechassignment.featurs.home.data.model.NotificationLocalModel
import com.elimone.samtechassignment.featurs.home.domain.model.NotificationDomainModel


fun NotificationLocalModel.toDomainModel() = NotificationDomainModel(
    id, title, body, time
)