import SwiftUI

struct AppButton: View {
    let title: String
    let onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            Text(title)
                .font(.system(size: 20))
                .bold()
                .foregroundColor(Color.textPrimary)
                .padding([.vertical], 16)
                .frame(minWidth: 0, maxWidth: .infinity)
                .background(Color.cardPrimary, in: RoundedRectangle(cornerRadius: 8))
  
        }
 
    }
}

struct AppButton_Previews: PreviewProvider {
    static var previews: some View {
        AppButton(title: "Button", onClick: {})
    }
}
