package ru.alexpanov.core.format

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.currentLocale

actual class DecimalFormat {
    @OptIn(ExperimentalForeignApi::class)
    actual fun format(
        double: Double,
        minFractionDigits: Int,
        maxFractionDigits: Int
    ): String {
        val formatter = NSNumberFormatter().apply {
            minimumFractionDigits = minFractionDigits.convert()
            maximumFractionDigits = maxFractionDigits.convert()
            numberStyle = 1u // Decimal
            locale = NSLocale.currentLocale
        }
        return formatter.stringFromNumber(NSNumber(double))!!
    }
}