package ru.alexpanov.core.model

data class RocketSettings(
    val mass: MassUnit,
    val height: DistanceUnit,
    val diameter: DistanceUnit,
    val payloadWeight: MassUnit
)

enum class MassUnit {
    Kg,
    Lb
}

enum class DistanceUnit {
    Meters,
    Feet
}