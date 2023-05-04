import ComposeApp
import SwiftUI

struct RootView: View {
    private let root: Root
    @ObservedObject
    private var childStack: ObservableValue<ChildStack<AnyObject, RootChild>>
    
    @ObservedObject
    private var childSlot: ObservableValue<ChildSlot<AnyObject, RootSlotChild>>
    
    private var stack: ChildStack<AnyObject, RootChild> { childStack.value }
    
    init(_ root: Root) {
        self.root = root
        childStack = .init(root.childStack)
        childSlot = .init(root.childSlot)
    }
    
    var body: some View {
        StackView(
            stackValue: childStack,
            getTitle: { _ in "Ttile" },
            onBack: root.onBackClicked,
            childContent: ChildView.init
        )
        .accentColor(.textPrimary)
        .preferredColorScheme(.dark)
        .sheet(
            item: childSlot.value.child?.instance,
            onDismiss: { root.dismissOverlay() },
            content: { child in
                switch child {
                case let child as RootSlotChild.SettingsChild:
                    SettingsView(child.component)
                default:
                  EmptyView()
                }
            }
        )

    }
}

private struct ChildView: View {
    let child: RootChild
    
    var body: some View {
        switch child {
        case let child as RootChild.RocketsChild:
            RocketsView(child.component)
                .navigationBarHidden(true)
                .navigationBarTitle(Text("Rockets"))
        case let child as RootChild.LaunchesChild:
            LaunchesView(child.component)
                .navigationBarTitle(child.component.rocketName, displayMode: .inline)
        default:
            EmptyView()
        }
    }
}
