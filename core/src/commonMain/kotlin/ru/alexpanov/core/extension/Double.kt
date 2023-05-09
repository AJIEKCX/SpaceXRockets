package ru.alexpanov.core.extension

import ru.alexpanov.core.format.DecimalFormat

fun Double.format(): String {
    return DecimalFormat().format(
        double = this,
        minFractionDigits = 0,
        maxFractionDigits = 2
    )
}