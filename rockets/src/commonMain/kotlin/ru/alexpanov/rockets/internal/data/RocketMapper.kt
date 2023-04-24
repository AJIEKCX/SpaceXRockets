package ru.alexpanov.rockets.internal.data

import ru.alexpanov.core_network.model.ApiRocket
import ru.alexpanov.rockets.internal.domain.model.PayloadWeight
import ru.alexpanov.rockets.internal.domain.model.Rocket
import ru.alexpanov.rockets.internal.domain.model.RocketDiameter
import ru.alexpanov.rockets.internal.domain.model.RocketHeight
import ru.alexpanov.rockets.internal.domain.model.RocketMass
import ru.alexpanov.rockets.internal.domain.model.RocketStage

internal fun ApiRocket.toDomain(): Rocket {
    return Rocket(
        id = id,
        name = name,
        costPerLaunch = costPerLaunch,
        firstFlight = firstFlight,
        country = country,
        height = RocketHeight(meters = height.meters, feet = height.feet),
        diameter = RocketDiameter(meters = diameter.meters, feet = diameter.feet),
        mass = RocketMass(kg = mass.kg, lb = mass.lb),
        payloadWeight = payloadWeights.first().let {
            PayloadWeight(id = it.id, name = it.name, kg = it.kg, lb = it.lb)
        },
        firstStage = RocketStage(
            engines = firstStage.engines,
            fuelAmountTons = firstStage.fuelAmountTons,
            burnTimeSec = firstStage.burnTimeSec
        ),
        secondStage = RocketStage(
            engines = secondStage.engines,
            fuelAmountTons = secondStage.fuelAmountTons,
            burnTimeSec = secondStage.burnTimeSec
        ),
        flickrImage = flickrImages.first()
    )
}