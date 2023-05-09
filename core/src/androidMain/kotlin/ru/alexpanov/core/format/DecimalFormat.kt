package ru.alexpanov.core.format

import java.text.NumberFormat
import java.util.Locale

actual class DecimalFormat {
    actual fun format(
        double: Double,
        minFractionDigits: Int,
        maxFractionDigits: Int
    ): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        numberFormat.maximumFractionDigits = maxFractionDigits
        numberFormat.minimumFractionDigits = minFractionDigits

        return numberFormat.format(double).trim()
    }
}