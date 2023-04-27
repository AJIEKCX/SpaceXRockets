import SwiftUI

extension Color {
    static let textPrimary = Color(hex: 0xFFFFFF)
    static let textSecondary = Color(hex: 0x8E8E8F)
    static let textTertiary = Color(hex: 0xCACACA)
    static let cardPrimary = Color(hex: 0x212121)
    static let background = Color(hex: 0x000000)
    
    init(hex: UInt, alpha: Double = 1) {
      self.init(
        .sRGB,
        red: Double((hex >> 16) & 0xff) / 255,
        green: Double((hex >> 08) & 0xff) / 255,
        blue: Double((hex >> 00) & 0xff) / 255,
        opacity: alpha
      )
    }
}
