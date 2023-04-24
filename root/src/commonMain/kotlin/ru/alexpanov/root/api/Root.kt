package ru.alexpanov.root.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.alexpanov.launches.api.Launches
import ru.alexpanov.rockets.api.Rockets

interface Root {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class RocketsChild(val component: Rockets) : Child()
        class LaunchesChild(val component: Launches) : Child()
    }
}