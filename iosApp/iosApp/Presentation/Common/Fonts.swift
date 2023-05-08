import SwiftUI
import ComposeApp

enum FontStyle {
    /// Regular, 24|32
    case h5
    
    /// Regular, 20|28
    case h6
    
    /// Bold, 16|24
    case subtitle1
    
    /// Regular, 16|24
    case body1
    
    /// Regular, 14|20
    case body2
    
    /// Bold, 20|28
    case button1
    
    func lineSpacing() -> CGFloat {
      switch self {
      case .body2:
        return 3
      default:
        return 2
      }
    }
}

extension Font {
    static func preferredFont(
        _ style: FontStyle = .body1
    ) -> Font {
        let regularFont = LabGrotesqueFont().regular
        let boldFont = LabGrotesqueFont().bold
        
        let fontAttributes: [FontStyle: (size: CGFloat, fontRes: FontResource)] = [
            .h5: (24, regularFont),
            .h6: (20, regularFont),
            .subtitle1: (16, boldFont),
            .body1: (16, regularFont),
            .body2: (14, regularFont),
            .button1: (20, boldFont),
        ]
        
        let fontAttribute = fontAttributes[style] ?? (16, regularFont)
        
        return Font(uiFont: fontAttribute.fontRes.uiFont(withSize: fontAttribute.size))
    }
}
