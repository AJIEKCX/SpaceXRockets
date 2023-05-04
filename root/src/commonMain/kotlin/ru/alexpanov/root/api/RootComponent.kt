package ru.alexpanov.root.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import io.ktor.client.HttpClient
import ru.alexpanov.core.repository.SettingsRepository
import ru.alexpanov.core_network.provider.HttpClientProvider
import ru.alexpanov.core_network.provider.JsonProvider
import ru.alexpanov.launches.api.LaunchesComponent
import ru.alexpanov.launches.api.LaunchesDependencies
import ru.alexpanov.rockets.api.RocketsComponent
import ru.alexpanov.rockets.api.RocketsDependencies
import ru.alexpanov.root.internal.data.repository.DefaultSettingsRepository
import ru.alexpanov.settings.api.DefaultSettingsComponent
import ru.alexpanov.settings.api.SettingsDependencies

class RootComponent(
    componentContext: ComponentContext
) : Root, ComponentContext by componentContext {
    private val navigation = StackNavigation<ScreenConfig>()

    override val childStack: Value<ChildStack<*, Root.Child>> = childStack(
        source = navigation,
        handleBackButton = true,
        initialStack = { listOf(ScreenConfig.Rockets) },
        childFactory = ::child,
    )

    private val slotNavigation = SlotNavigation<SlotConfig>()

    override val childSlot: Value<ChildSlot<*, Root.SlotChild>> = childSlot(
        source = slotNavigation,
        handleBackButton = true,
        childFactory = ::child
    )

    override fun dismissOverlay() {
        slotNavigation.dismiss()
    }

    override fun onBackClicked() {
        navigation.pop()
    }

    private fun child(
        config: ScreenConfig,
        componentContext: ComponentContext
    ): Root.Child {
        return when (config) {
            is ScreenConfig.Rockets -> {
                Root.Child.RocketsChild(
                    RocketsComponent(
                        componentContext = componentContext,
                        dependencies = object : RocketsDependencies {
                            override val httpClient: HttpClient = HttpClientProvider(JsonProvider().get()).get()
                            override val settingsRepository: SettingsRepository = DefaultSettingsRepository()
                        },
                        navigateLaunches = { rocket ->
                            navigation.push(
                                ScreenConfig.Launches(rocketId = rocket.id, rocketName = rocket.name)
                            )
                        },
                        navigateSettings = {
                            slotNavigation.activate(SlotConfig.Settings)
                        }
                    )
                )
            }
            is ScreenConfig.Launches -> {
                Root.Child.LaunchesChild(
                    LaunchesComponent(
                        rocketName = config.rocketName,
                        rocketId = config.rocketId,
                        dependencies = object : LaunchesDependencies {
                            override val httpClient: HttpClient = HttpClientProvider(JsonProvider().get()).get()
                        },
                        componentContext = componentContext
                    )
                )
            }
        }
    }

    private fun child(
        config: SlotConfig,
        componentContext: ComponentContext
    ): Root.SlotChild {
        return when (config) {
            is SlotConfig.Settings -> {
                Root.SlotChild.SettingsChild(
                    DefaultSettingsComponent(
                        componentContext = componentContext,
                        onDismiss = slotNavigation::dismiss,
                        dependencies = object : SettingsDependencies {
                            override val settingsRepository: SettingsRepository
                                get() = DefaultSettingsRepository()
                        }
                    )
                )
            }
        }
    }
}

sealed interface ScreenConfig : Parcelable {
    @Parcelize
    object Rockets : ScreenConfig

    @Parcelize
    data class Launches(
        val rocketId: String,
        val rocketName: String
    ) : ScreenConfig
}

sealed interface SlotConfig : Parcelable {
    @Parcelize
    object Settings : SlotConfig
}