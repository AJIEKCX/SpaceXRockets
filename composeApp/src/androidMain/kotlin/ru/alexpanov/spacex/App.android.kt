package ru.alexpanov.spacex

import RocketsScreen
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RootScreen(
    root: Root,
    modifier: Modifier = Modifier
) {
    val childStack by root.childStack.subscribeAsState()
    val childSlot by root.childSlot.subscribeAsState()

    val bottomSheetState = rememberSlotModalBottomSheetState(
        child = childSlot.child?.instance
    ) { slotChild ->
        when (slotChild) {
            is Root.SlotChild.SettingsChild -> SettingsScreen(component = slotChild.component)
        }
    }

    if (bottomSheetState.isVisible.value) {
        ModalBottomSheet(
            sheetState = bottomSheetState.sheetState,
            onDismissRequest = root::dismissSlotChild,
            content = bottomSheetState.sheetContent.value,
            containerColor = MaterialTheme.colorScheme.pagerIndicatorBackground,
            dragHandle = null
        )
    }

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
                            title = component.rocketName,
                            onBackClicked = component::onBackClicked
                        )
                    }
                )
        }
    }
}