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
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreateSimple
import kotlinx.serialization.Serializable
import ru.alexpanov.launches.api.LaunchesComponent
import ru.alexpanov.rockets.api.RocketsComponent
import ru.alexpanov.root.internal.di.DataModule
import ru.alexpanov.root.internal.di.RootModule
import ru.alexpanov.settings.api.DefaultSettingsComponent

class RootComponent(
    componentContext: ComponentContext
) : Root, ComponentContext by componentContext {

    private val rootModule = instanceKeeper.getOrCreateSimple {
        RootModule(DataModule())
    }

    private val navigation = StackNavigation<ScreenConfig>()

    override val childStack: Value<ChildStack<*, Root.Child>> = childStack(
        source = navigation,
        serializer = ScreenConfig.serializer(),
        handleBackButton = true,
        initialStack = { listOf(ScreenConfig.Rockets) },
        childFactory = ::child,
    )

    private val slotNavigation = SlotNavigation<SlotConfig>()

    override val childSlot: Value<ChildSlot<*, Root.SlotChild>> = childSlot(
        source = slotNavigation,
        serializer = SlotConfig.serializer(),
        handleBackButton = true,
        childFactory = ::child
    )

    override fun dismissSlotChild() {
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
                        dependencies = rootModule,
                        navigateLaunches = { rocket ->
                            navigation.pushNew(
                                ScreenConfig.Launches(
                                    rocketId = rocket.id,
                                    rocketName = rocket.name
                                )
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
                        dependencies = rootModule,
                        navigateBack = navigation::pop,
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
                        dependencies = rootModule,
                    )
                )
            }
        }
    }
}

@Serializable
sealed interface ScreenConfig {
    @Serializable
    data object Rockets : ScreenConfig

    @Serializable
    data class Launches(
        val rocketId: String,
        val rocketName: String
    ) : ScreenConfig
}

@Serializable
sealed interface SlotConfig {
    @Serializable
    data object Settings : SlotConfig
}