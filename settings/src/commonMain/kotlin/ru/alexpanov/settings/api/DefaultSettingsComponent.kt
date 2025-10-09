package ru.alexpanov.settings.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.core.flow.wrapToAny
import ru.alexpanov.core.model.DistanceUnit
import ru.alexpanov.core.model.MassUnit
import ru.alexpanov.core.model.RocketSettings
import ru.alexpanov.settings.internal.di.SettingsModule
import ru.alexpanov.settings.internal.presentation.SettingsFeature

class DefaultSettingsComponent(
    componentContext: ComponentContext,
    dependencies: SettingsDependencies,
    private val onDismiss: () -> Unit
) : SettingsComponent, ComponentContext by componentContext {

    private val feature: SettingsFeature = instanceKeeper.getOrCreate {
        val module = SettingsModule(dependencies)
        module.settingsFeature
    }

    override val state: AnyStateFlow<RocketSettings> = feature.state.wrapToAny()

    override fun onDismissClick() {
        onDismiss()
    }

    override fun onHeightChanged(value: DistanceUnit) {
        feature.onHeightChanged(value)
    }

    override fun onDiameterChanged(value: DistanceUnit) {
        feature.onDiameterChanged(value)
    }

    override fun onMassChanged(value: MassUnit) {
        feature.onMassChanged(value)
    }

    override fun onPayloadWeightChanged(value: MassUnit) {
        feature.onPayloadWeightChanged(value)
    }
}