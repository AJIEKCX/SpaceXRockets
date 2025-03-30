package ru.alexpanov.spacex

import RocketsScreen
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.isFront
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.alexpanov.root.api.Root
import ru.alexpanov.root.api.RootComponent
import ru.alexpanov.spacex.common.rememberSlotModalBottomSheetState
import ru.alexpanov.spacex.launches.LaunchesAppBar
import ru.alexpanov.spacex.launches.LaunchesScreen
import ru.alexpanov.spacex.settings.SettingsScreen
import ru.alexpanov.spacex.theme.AppTheme
import ru.alexpanov.spacex.theme.pagerIndicatorBackground

class AndroidApp : Application()

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val root = RootComponent(defaultComponentContext())
        setContent {
            AppTheme {
                RootScreen(root)
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun RootScreen(
    root: Root,
    modifier: Modifier = Modifier
) {
    val childStack by root.childStack.subscribeAsState()

    val bottomSheetState = rememberSlotModalBottomSheetState(
        root.childSlot,
        onDismiss = root::dismissSlotChild
    ) { slotChild ->
        when (val child = slotChild.instance) {
            is Root.SlotChild.SettingsChild -> SettingsScreen(component = child.component)
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState.sheetState,
        sheetContent = bottomSheetState.sheetContent.value,
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = MaterialTheme.colors.pagerIndicatorBackground
    ) {
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
                                contentPadding = WindowInsets.statusBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
                                    .asPaddingValues()
                            )
                        }
                    )
            }
        }
    }
}