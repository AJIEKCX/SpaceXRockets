import SwiftUI
import ComposeApp

struct RocketsView: View {
    let component: Rockets
    
    @ObservedObject private var observableState: ObservableStateFlow<RocketsUiState>
    
    init(_ component: Rockets) {
        self.component = component
        observableState = .init(component.state)
        
        UIScrollView.appearance().contentInsetAdjustmentBehavior = .never
    }
    
    var body: some View {
        switch observableState.value {
        case is RocketsUiState.Loading:
            ProgressView()
        case is RocketsUiState.Error:
            Text("Error")
        case let data as RocketsUiState.Data:
            content(rockets: data.rockets)
        default:
            EmptyView()
        }
    }
    
    @ViewBuilder
    func content(rockets: Array<RocketUiModel>) -> some View {
        TabView {
            ForEach(rockets, id: \.self) { rocket in
                RocketContentView(
                    rocket: rocket,
                    onLaunchesClick: component.onLaunchesClick,
                    onSettingsClick: component.onSettingsClick
                )
            }
        }
        .tabViewStyle(.page)
        .indexViewStyle(.page(backgroundDisplayMode: .interactive))
        .onAppear {
            UIScrollView.appearance().bounces = false
            UIPageControl.appearance().currentPageIndicatorTintColor = UIColor(Color.white)
            UIPageControl.appearance().pageIndicatorTintColor = UIColor(Color.gray)
        }
        .ignoresSafeArea()
    }
}
