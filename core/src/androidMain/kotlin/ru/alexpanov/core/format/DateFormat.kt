package ru.alexpanov.core.format

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import ru.alexpanov.core.date.DatePattern
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.Locale

actual class DateFormat {
    actual fun format(date: LocalDate, pattern: DatePattern): String {
        return date.toJavaLocalDate().format(pattern)
    }

    actual fun format(dateTime: LocalDateTime, pattern: DatePattern): String {
        return dateTime.toJavaLocalDateTime().format(pattern)
    }

    private fun TemporalAccessor.format(pattern: DatePattern): String {
        val formatter = DateTimeFormatter.ofPattern(pattern.value, Locale.getDefault())
        return formatter.format(this)
    }
}