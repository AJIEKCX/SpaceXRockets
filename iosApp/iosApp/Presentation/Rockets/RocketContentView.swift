import SwiftUI
import ComposeApp

struct RocketContentView: View {
    let rocket: RocketUiModel
    let onLaunchesClick: (String) -> Void
    let onSettingsClick: () -> Void
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 0) {
                AsyncImage(url: URL(string: rocket.flickrImage)) { image in
                    image
                        .resizable()
                        .aspectRatio(1, contentMode: .fill)
                } placeholder: {
                    ProgressView()
                        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                }
                .frame(height: 250, alignment: .top)
                
                VStack(alignment: .leading, spacing: 0) {
                    HStack {
                        Text(rocket.name)
                            .font(.system(size: 24))
                            .foregroundColor(.textPrimary)
                            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)

                        Image(systemName: "gearshape")
                            .resizable()
                            .frame(width: 24, height: 24)
                            .onTapGesture {
                                onSettingsClick()
                            }
                    }
                    .padding([.top], 48)
                    .padding([.horizontal], 32)
                 
                    
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack(spacing: 12) {
                            ForEach(rocket.params, id: \.self) { param in
                                RocketParamCell(param: param)
                            }
                        }
                        .padding([.horizontal], 20)
                    }
                    .padding([.top], 32)
                    
                    VStack(spacing: 16) {
                        RocketInfoCell(title: "First flight", value: rocket.firstFlight)
                        RocketInfoCell(title: "Country", value: rocket.country)
                        RocketInfoCell(title: "Cost per launch", value: rocket.costPerLaunch)
                    }
                    .padding([.horizontal], 32)
                    .padding([.top], 40)
        
                    RocketStageCell(title: "First stage", stage: rocket.firstStage)
                        .padding([.horizontal], 32)
                        .padding([.top], 40)
                    
                    RocketStageCell(title: "Second stage", stage: rocket.secondStage)
                        .padding([.horizontal], 32)
                        .padding([.top], 40)
                    
                    AppButton(
                        title: "Show launches",
                        onClick: { onLaunchesClick(rocket.id) }
                    )
                    .padding([.vertical], 40)
                    .padding([.horizontal], 32)
                }
                .background(Color.background)
                .cornerRadius(32, corners: [.topLeft, .topRight])
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .ignoresSafeArea(edges: .top)
    }
}

struct RocketInfoCell: View {
    let title: String
    let value: String
    
    var body: some View {
        HStack {
            Text(title)
                .font(.system(size: 16))
                .foregroundColor(.textTertiary)
            Spacer()
            Text(value)
                .font(.system(size: 16))
                .foregroundColor(.textPrimary)
        }
    }
}

struct RocketParamCell: View {
    let param: RocketParamUiModel
    
    var body: some View {
        VStack(spacing: 0) {
            Text(param.value)
                .font(.system(size: 16))
                .bold()
                .foregroundColor(.textPrimary)
            Text(param.title)
                .font(.system(size: 14))
                .foregroundColor(.textSecondary)
        }
        .frame(minWidth: 96, minHeight: 96)
        .background(Color.cardPrimary, in: RoundedRectangle(cornerRadius: 32.0))
    }
}

struct RocketStageCell: View {
    let title: String
    let stage: RocketStageUiModel
    
    var body: some View {
        VStack(spacing: 16) {
            Text(title)
                .font(.system(size: 16))
                .bold()
                .textCase(.uppercase)
                .foregroundColor(.textPrimary)
                .frame(maxWidth: .infinity, alignment: .leading)
            stageInfoCell(title: "Engines count", value: stage.engines)
            stageInfoCell(title: "Fuel amount", value: stage.fuelAmountTons, unit: "ton")
            stageInfoCell(title: "Burn time sec", value: stage.burnTimeSec, unit: "sec")
        }
    }
    
    @ViewBuilder
    func stageInfoCell(
        title: String,
        value: String,
        unit: String = ""
    ) -> some View {
        HStack(spacing: 8) {
            Text(title)
                .font(.system(size: 16))
                .foregroundColor(.textTertiary)
            Spacer()
            Text(value)
                .font(.system(size: 16))
                .bold()
                .foregroundColor(.textPrimary)
            Text(unit)
                .font(.system(size: 16))
                .foregroundColor(.textSecondary)
        }
    }
}
