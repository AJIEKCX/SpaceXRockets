package ru.alexpanov.spacex

import RocketsScreen
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.isFront
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import ru.alexpanov.root.api.Root
import ru.alexpanov.root.api.RootComponent

class AndroidApp : Application()

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = RootComponent(defaultComponentContext())
        setContent { SimpleDecomposeNavigation(root) }
    }
}


@Composable
internal fun SimpleDecomposeNavigation(root: Root) {
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
            }
        ) {
            when (val child = it.instance) {
                is Root.Child.RocketsChild ->
                    RocketsScreen(component = child.component)
                is Root.Child.LaunchesChild ->
                    LaunchesScreen(component = child.component)
            }
        }
    }
}