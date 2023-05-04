import SwiftUI

extension View {
    func sheet<T, Content>(
        item: T?,
        onDismiss: @escaping () -> Void,
        @ViewBuilder content: @escaping (T) -> Content
    ) -> some View where Content: View {
        sheet(
            isPresented: .init(
                get: { item != nil },
                set: { _ in }
            ),
            onDismiss: onDismiss,
            content: { content(item!) }
        )
    }
}
