import ComposeApp
import SwiftUI

struct RootView: View {
    private let root: Root
    @ObservedObject private var childStack: ObservableValue<ChildStack<AnyObject, RootChild>>
    private var activeChild: RootChild {
        childStack.value.active.instance
    }
    
    init(_ root: Root) {
        self.root = root
        childStack = .init(root.childStack)
    }
    
    var body: some View {
        ChildView(child: activeChild)
            .frame(maxHeight: .infinity)
    }
}

private struct ChildView: View {
    let child: RootChild
    
    var body: some View {
        switch child {
        case let child as RootChild.RocketsChild:
            RocketsView(child.component)
        case let child as RootChild.LaunchesChild:
            EmptyView()
        default:
            EmptyView()
        }
    }
}
