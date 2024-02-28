package com.elimone.samtechassignment.featurs.home.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationUiModel(
    val title: String?,
    val id: String?,
    val body: String?,
    val time: String?
) : Parcelable