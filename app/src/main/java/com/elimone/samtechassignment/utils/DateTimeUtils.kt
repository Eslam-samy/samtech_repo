package com.elimone.samtechassignment.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentLocalDateTime(): LocalDateTime {
    return LocalDateTime.now()
}

fun getCurrentLocalDateTimeAsString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return getCurrentLocalDateTime().format(formatter)
}