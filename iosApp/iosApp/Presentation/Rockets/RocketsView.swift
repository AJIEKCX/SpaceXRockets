import SwiftUI
import ComposeApp

struct RocketsView: View {
    let component: Rockets
    @ObservedObject private var observableState: ObservableStateFlow<RocketsUiState>
    
    init(_ component: Rockets) {
      self.component = component
      observableState = .init(component.state)
    }
    
    var body: some View {
        switch observableState.value {
        case is RocketsUiState.Loading:
            ProgressView()
        case is RocketsUiState.Error:
            Text("Error")
        case let data as RocketsUiState.Data:
            Text("Data")
        default:
            EmptyView()
        }
    }
}
