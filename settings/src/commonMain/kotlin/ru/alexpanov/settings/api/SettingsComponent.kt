package ru.alexpanov.settings.api

import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.core.model.DistanceUnit
import ru.alexpanov.core.model.MassUnit
import ru.alexpanov.core.model.RocketSettings

interface SettingsComponent {
    val state: AnyStateFlow<RocketSettings>

    fun onDismissClick()

    fun onHeightChanged(value: DistanceUnit)
    fun onDiameterChanged(value: DistanceUnit)
    fun onMassChanged(value: MassUnit)
    fun onPayloadWeightChanged(value: MassUnit)
}