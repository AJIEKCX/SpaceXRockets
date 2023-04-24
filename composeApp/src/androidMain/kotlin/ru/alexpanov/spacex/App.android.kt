package ru.alexpanov.spacex

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import ru.alexpanov.root.api.RootComponent
import ru.alexpanov.root.api.Root

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

    Children(
        stack = childStack
    ) {
        when (val child = it.instance) {
            is Root.Child.RocketsChild ->
                RocketsScreen(component = child.component)
            is Root.Child.LaunchesChild ->
                LaunchesScreen(component = child.component)
        }
    }
}