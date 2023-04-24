package ru.alexpanov.launches.api

import com.arkivanov.decompose.ComponentContext

class LaunchesComponent(
    private val rocketId: String,
    componentContext: ComponentContext
) : Launches, ComponentContext by componentContext {
}