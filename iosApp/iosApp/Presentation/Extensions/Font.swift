import SwiftUI

public extension Font {
    init(uiFont: UIFont) {
        self = Font(uiFont as CTFont)
    }
}

extension View {
    func customFont(_ fontStyle: FontStyle) -> some View {
        modifier(CustomFont(fontStyle: fontStyle))
    }
}

extension Text {
    func customFont(_ fontStyle: FontStyle) -> some View {
        self
            .lineSpacing(fontStyle.lineSpacing())
            .modifier(CustomFont(fontStyle: fontStyle))
    }
}

private struct CustomFont: ViewModifier {
    var fontStyle: FontStyle
    
    func body(content: Content) -> some View {
        content
            .font(.preferredFont(fontStyle))
    }
}
