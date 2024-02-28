package com.elimone.samtechassignment.featurs.single_notifications.presentation.contract

import kotlinx.coroutines.flow.MutableStateFlow

data class SingleNotificationContract(
    val loading: Boolean = false,
    val id: String? =null,
    val title: MutableStateFlow<String?> = MutableStateFlow(""),
    val body: MutableStateFlow<String?> = MutableStateFlow(""),
    val confirmed:Boolean? = false
)