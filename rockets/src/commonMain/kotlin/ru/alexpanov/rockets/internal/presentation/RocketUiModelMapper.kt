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
            RocketParamUiModel("Высота", height.formatValue(settings.height)),
            RocketParamUiModel("Диаметр", diameter.formatValue(settings.diameter)),
            RocketParamUiModel("Масса", mass.formatValue(settings.mass)),
            RocketParamUiModel("Нагрузка", payloadWeight.formatValue(settings.payloadWeight))
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

private fun RocketHeight.formatValue(unit: DistanceUnit): String {
    return when (unit) {
        DistanceUnit.Meters -> "$meters, m"
        DistanceUnit.Feet -> "$feet, ft"
    }
}

private fun RocketDiameter.formatValue(unit: DistanceUnit): String {
    return when (unit) {
        DistanceUnit.Meters -> "$meters, m"
        DistanceUnit.Feet -> "$feet, ft"
    }
}

private fun RocketMass.formatValue(unit: MassUnit): String {
    return when (unit) {
        MassUnit.Kg -> "$kg, kg"
        MassUnit.Lb -> "$lb, lb"
    }
}

private fun PayloadWeight.formatValue(unit: MassUnit): String {
    return when (unit) {
        MassUnit.Kg -> "$kg, kg"
        MassUnit.Lb -> "$lb, lb"
    }
}