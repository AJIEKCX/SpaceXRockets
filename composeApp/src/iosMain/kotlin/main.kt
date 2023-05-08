import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import ru.alexpanov.launches.api.Launches
import ru.alexpanov.spacex.theme.AppTheme
import ru.alexpanov.spacex.launches.LaunchesScreen

fun LaunchesViewController(component: Launches): UIViewController {
    return ComposeUIViewController {
        AppTheme {
            LaunchesScreen(component)
        }
    }
}
