import UIKit
import SwiftUI
import ComposeApp

@main
struct iosApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

struct ContentView: View {
    let rootHolder = RootHolder()
    
    var body: some View {
        RootView(rootHolder.root)
            .onAppear {
              LifecycleRegistryExtKt.resume(rootHolder.lifecycle)
            }
            .onDisappear {
              LifecycleRegistryExtKt.stop(rootHolder.lifecycle)
            }
    }
}
