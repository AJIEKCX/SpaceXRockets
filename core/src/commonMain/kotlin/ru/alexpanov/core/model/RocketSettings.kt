package ru.alexpanov.core.model

data class RocketSettings(
    val mass: MassUnit,
    val height: DistanceUnit,
    val diameter: DistanceUnit,
    val payloadWeight: MassUnit
)

enum class MassUnit(val value: String) {
    Kg("kg"),
    Lb("lb")
}

enum class DistanceUnit(val value: String) {
    Meters("m"),
    Feet("ft")
}