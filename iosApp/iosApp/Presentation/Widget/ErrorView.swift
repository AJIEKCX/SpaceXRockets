import SwiftUI
import ComposeApp

struct ErrorView: View {
    let onClick: () -> Void
    
    var body: some View {
        VStack {
            Image(uiImage: MR.images().spacex_rocket.toUIImage()!)
                .padding(16)
                .background(Color.textPrimary, in: Circle())
            
            Text(MR.strings().error_stub_text.desc().localized())
                .customFont(.h6)
                .padding([.top], 48)
            
            AppButton(
                title: MR.strings().error_stub_repeat_btn.desc().localized(),
                onClick: onClick
            )
            .padding([.top], 48)
            .padding([.horizontal], 32)
        }
    }
}
