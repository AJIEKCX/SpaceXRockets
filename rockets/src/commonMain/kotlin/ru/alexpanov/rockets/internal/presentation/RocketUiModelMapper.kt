package ru.alexpanov.rockets.internal.presentation

import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc
import ru.alexpanov.core.date.DatePattern
import ru.alexpanov.core.extension.format
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
import ru.alexpanov.spacex.rockets.RocketsR

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
        firstFlight = firstFlight.format(DatePattern.D_MMM_YYYY),
        country = country,
        costPerLaunch = formatCostPerLaunch(costPerLaunch),
        firstStage = firstStage.toUiModel(),
        secondStage = secondStage.toUiModel(),
        flickrImage = flickrImage
    )
}

private fun formatCostPerLaunch(value: Long): StringDesc {
    val valueInMillions = value / 1_000_000
    return StringDesc.ResourceFormatted(RocketsR.strings.rocket_cost_value, valueInMillions)
}

private fun RocketStage.toUiModel(): RocketStageUiModel {
    return RocketStageUiModel(
        engines = engines.toString(),
        fuelAmountTons = fuelAmountTons.format(),
        burnTimeSec = burnTimeSec?.toString() ?: "—"
    )
}

private fun RocketHeight.toUiModel(unit: DistanceUnit): RocketParamUiModel {
    val value = when (unit) {
        DistanceUnit.Meters -> meters
        DistanceUnit.Feet -> feet
    }

    val title = StringDesc.ResourceFormatted(RocketsR.strings.rocket_param_height, unit.value)
    return RocketParamUiModel(title = title, value.format())
}

private fun RocketDiameter.toUiModel(unit: DistanceUnit): RocketParamUiModel {
    val value = when (unit) {
        DistanceUnit.Meters -> meters
        DistanceUnit.Feet -> feet
    }
    val title = StringDesc.ResourceFormatted(RocketsR.strings.rocket_param_diameter, unit.value)
    return RocketParamUiModel(title = title, value.format())
}

private fun RocketMass.toUiModel(unit: MassUnit): RocketParamUiModel {
    val value = when (unit) {
        MassUnit.Kg -> kg
        MassUnit.Lb -> lb
    }
    val title = StringDesc.ResourceFormatted(RocketsR.strings.rocket_param_mass, unit.value)
    return RocketParamUiModel(title = title, value.toString())
}

private fun PayloadWeight.toUiModel(unit: MassUnit): RocketParamUiModel {
    val value = when (unit) {
        MassUnit.Kg -> kg
        MassUnit.Lb -> lb
    }
    val title = StringDesc.ResourceFormatted(RocketsR.strings.rocket_param_payload, unit.value)
    return RocketParamUiModel(title = title, value.toString())
}