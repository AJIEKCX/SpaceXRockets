package ru.alexpanov.core.format

expect class DecimalFormat() {
    fun format(
        double: Double,
        minFractionDigits: Int,
        maxFractionDigits: Int
    ): String
}