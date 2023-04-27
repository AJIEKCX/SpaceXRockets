package ru.alexpanov.rockets.internal.presentation

import ru.alexpanov.core.model.DistanceUnit
import ru.alexpanov.core.model.MassUnit
import ru.alexpanov.core.model.RocketSettings
import ru.alexpanov.rockets.api.data.RocketParamUiModel
import ru.alexpanov.rockets.api.data.RocketStageUiModel
import ru.alexpanov.rockets.api.data.RocketUiModel
import ru.alexpanov.rockets.internal.domain.model.PayloadWeight
import ru.alexpanov.rockets.internal.domain.model.Rocket
import ru.alexpanov.rockets.internal.domain.model.RocketDiameter
import ru.alexpanov.rockets.internal.domain.model.RocketHeight
import ru.alexpanov.rockets.internal.domain.model.RocketMass
import ru.alexpanov.rockets.internal.domain.model.RocketStage

internal fun Rocket.toUiModel(settings: RocketSettings): RocketUiModel {
    return RocketUiModel(
        id = id,
        name = name,
        params = listOf(
            height.toUiModel(settings.height),
            diameter.toUiModel(settings.diameter),
            mass.toUiModel(settings.mass),
            payloadWeight.toUiModel(settings.payloadWeight)
        ),
        firstFlight = firstFlight,
        country = country,
        costPerLaunch = costPerLaunch.toString(),
        firstStage = firstStage.toUiModel(),
        secondStage = secondStage.toUiModel(),
        flickrImage = flickrImage
    )
}

private fun RocketStage.toUiModel(): RocketStageUiModel {
    return RocketStageUiModel(
        engines = engines.toString(),
        fuelAmountTons = fuelAmountTons.toString(),
        burnTimeSec = burnTimeSec?.toString() ?: "-"
    )
}

private fun RocketHeight.toUiModel(unit: DistanceUnit): RocketParamUiModel {
    val value = when (unit) {
        DistanceUnit.Meters -> meters
        DistanceUnit.Feet -> feet
    }
    return RocketParamUiModel(title = "Height, ${unit.value}", value.toString())
}

private fun RocketDiameter.toUiModel(unit: DistanceUnit): RocketParamUiModel {
    val value = when (unit) {
        DistanceUnit.Meters -> meters
        DistanceUnit.Feet -> feet
    }
    return RocketParamUiModel(title = "Diameter, ${unit.value}", value.toString())
}

private fun RocketMass.toUiModel(unit: MassUnit): RocketParamUiModel {
    val value = when (unit) {
        MassUnit.Kg -> kg
        MassUnit.Lb -> lb
    }
    return RocketParamUiModel(title = "Mass, ${unit.value}", value.toString())
}

private fun PayloadWeight.toUiModel(unit: MassUnit): RocketParamUiModel {
    val value = when (unit) {
        MassUnit.Kg -> kg
        MassUnit.Lb -> lb
    }
    return RocketParamUiModel(title = "Payload, ${unit.value}", value.toString())
}