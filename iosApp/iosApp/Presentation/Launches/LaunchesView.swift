import SwiftUI
import ComposeApp

struct LaunchesView: View {
    let component: Launches
    
    init(_ component: Launches) {
        self.component = component
    }
    
    var body: some View {
        LaunchesContentView(component: component)
            .ignoresSafeArea(edges: .bottom)
    }
}

struct LaunchesContentView: UIViewControllerRepresentable {
    let component: Launches
    
    func makeUIViewController(context: Context) -> some UIViewController {
        MainKt.LaunchesViewController(component: component)
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
}
