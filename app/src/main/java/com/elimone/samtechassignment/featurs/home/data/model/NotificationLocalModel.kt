package com.elimone.samtechassignment.featurs.home.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "notification")
data class NotificationLocalModel(
    val title: String?,
    @PrimaryKey
    val id: String,
    val body: String?,
    val time: String?
)
