package ru.alexpanov.core.format

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.alexpanov.core.date.DatePattern

expect class DateFormat() {
    fun format(dateTime: LocalDateTime, pattern: DatePattern): String
    fun format(date: LocalDate, pattern: DatePattern): String
}