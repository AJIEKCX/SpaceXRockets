package ru.alexpanov.core.extension

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.alexpanov.core.date.DatePattern
import ru.alexpanov.core.format.DateFormat

fun LocalDateTime.format(pattern: DatePattern): String {
    return DateFormat().format(this, pattern)
}

fun LocalDate.format(pattern: DatePattern): String {
    return DateFormat().format(this, pattern)
}