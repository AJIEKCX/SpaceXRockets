package ru.alexpanov.rockets.internal.domain.model

internal data class Rocket(
    val id: String,
    val name: String,
    val costPerLaunch: Long,
    val firstFlight: String,
    val country: String,
    val height: RocketHeight,
    val diameter: RocketDiameter,
    val mass: RocketMass,
    val payloadWeight: PayloadWeight,
    val firstStage: RocketStage,
    val secondStage: RocketStage,
    val flickrImage: String
)

internal data class RocketHeight(
    val meters: Double,
    val feet: Double
)

internal data class RocketDiameter(
    val meters: Double,
    val feet: Double
)

internal data class RocketMass(
    val kg: Int,
    val lb: Int
)

internal data class PayloadWeight(
    val id: String,
    val name: String,
    val kg: Int,
    val lb: Int
)

internal data class RocketStage(
    val engines: Int,
    val fuelAmountTons: Double,
    val burnTimeSec: Int?
)
