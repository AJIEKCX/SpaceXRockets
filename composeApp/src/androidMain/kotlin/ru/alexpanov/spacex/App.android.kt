package ru.alexpanov.spacex

import RocketsScreen
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.isFront
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.alexpanov.root.api.Root
import ru.alexpanov.root.api.RootComponent
import ru.alexpanov.spacex.launches.LaunchesAppBar
import ru.alexpanov.spacex.launches.LaunchesScreen

class AndroidApp : Application()

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val root = RootComponent(defaultComponentContext())
        setContent {
            val systemUiController = rememberSystemUiController()

            DisposableEffect(systemUiController) {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = false
                )
                onDispose {}
            }

            RootScreen(root)
        }
    }
}


@Composable
internal fun RootScreen(
    root: Root,
    modifier: Modifier = Modifier
) {
    val childStack by root.childStack.subscribeAsState()

    AppTheme {
        Children(
            stack = childStack,
            animation = stackAnimation { from, to, direction ->
                if (direction.isFront) {
                    slide() + fade()
                } else {
                    scale(frontFactor = 1F, backFactor = 0.7F) + fade()
                }
            },
            modifier = modifier
        ) {
            when (val child = it.instance) {
                is Root.Child.RocketsChild ->
                    RocketsScreen(component = child.component)
                is Root.Child.LaunchesChild ->
                    LaunchesScreen(
                        component = child.component,
                        topBarContent = { component ->
                            LaunchesAppBar(
                                component = component,
                                contentPadding = WindowInsets.statusBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top).asPaddingValues()
                            )
                        }
                    )
            }
        }
    }
}