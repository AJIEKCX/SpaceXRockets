package ru.alexpanov.spacex.common

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.launch

private val emptyContent: @Composable ColumnScope.() -> Unit = {}

@ExperimentalMaterial3Api
class SlotModalBottomSheetState(
    val sheetContent: State<@Composable ColumnScope.() -> Unit>,
    val isVisible: State<Boolean>,
    val sheetState: SheetState,
)

@ExperimentalMaterial3Api
@Composable
fun <T : Any> rememberSlotModalBottomSheetState(
    child: T?,
    confirmValueChange: (SheetValue) -> Boolean = { true },
    skipPartiallyExpanded: Boolean = false,
    sheetContent: @Composable (child: T) -> Unit,
): SlotModalBottomSheetState {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
        confirmValueChange = confirmValueChange
    )
    val isVisible = remember { mutableStateOf(child != null) }
    val childContent = remember { mutableStateOf(emptyContent) }

    LaunchedEffect(child == null) {
        if (child == null) {
            launch { sheetState.hide() }
                .invokeOnCompletion {
                    isVisible.value = false
                    childContent.value = emptyContent
                }
        } else {
            isVisible.value = true
        }
    }

    DisposableEffect(child) {
        if (child != null) {
            childContent.value = { sheetContent(child) }
        }
        onDispose {}
    }

    return remember {
        SlotModalBottomSheetState(
            sheetContent = childContent,
            sheetState = sheetState,
            isVisible = isVisible
        )
    }
}