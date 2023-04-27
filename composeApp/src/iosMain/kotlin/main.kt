import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import ru.alexpanov.launches.api.Launches
import ru.alexpanov.spacex.LaunchesScreen

fun LaunchesViewController(component: Launches): UIViewController {
    return ComposeUIViewController { LaunchesScreen(component) }
}
