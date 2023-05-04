package ru.alexpanov.settings.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.core.flow.wrapToAny
import ru.alexpanov.core.model.DistanceUnit
import ru.alexpanov.core.model.MassUnit
import ru.alexpanov.core.model.RocketSettings
import ru.alexpanov.settings.internal.di.createSettingsModules
import ru.alexpanov.settings.internal.presentation.SettingsFeature
import ru.kontur.core_koin.ComponentKoinContext

class DefaultSettingsComponent(
    componentContext: ComponentContext,
    dependencies: SettingsDependencies,
    private val onDismiss: () -> Unit
) : SettingsComponent, ComponentContext by componentContext {
    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        createSettingsModules(dependencies)
    )

    private val feature: SettingsFeature = instanceKeeper.getOrCreate { scope.get() }

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