package ru.evgenykuzakov.common.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun String.toStringDate(): String {
    val date = Instant.parse(this)
    val zonedDate = date.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return formatter.format(zonedDate) ?: ""
}