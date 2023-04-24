import ComposeApp
import SwiftUI

final class RootHolder {
  let lifecycle: LifecycleRegistry
  let root: Root
  
  init() {
    lifecycle = LifecycleRegistryKt.LifecycleRegistry()
    root = RootComponent(
      componentContext: DefaultComponentContext(lifecycle: lifecycle)
    )
    lifecycle.onCreate()
  }
  
  deinit {
    lifecycle.onDestroy()
  }
}
