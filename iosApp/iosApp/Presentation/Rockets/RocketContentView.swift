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
                            .customFont(.h5)
                            .foregroundColor(.textPrimary)
                            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                        
                        Image(uiImage: MR.images().settings.toUIImage()!)
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
                        RocketInfoCell(
                            title:  RocketsR.strings().rocket_first_flight.desc().localized(),
                            value: rocket.firstFlight
                        )
                        RocketInfoCell(
                            title: RocketsR.strings().rocket_country.desc().localized(),
                            value: rocket.country
                        )
                        RocketInfoCell(
                            title: RocketsR.strings().rocket_cost_per_launch.desc().localized(),
                            value: rocket.costPerLaunch
                        )
                    }
                    .padding([.horizontal], 32)
                    .padding([.top], 40)
                    
                    RocketStageCell(
                        title: RocketsR.strings().rocket_stage_first.desc().localized(),
                        stage: rocket.firstStage
                    )
                    .padding([.horizontal], 32)
                    .padding([.top], 40)
                    
                    RocketStageCell(
                        title: RocketsR.strings().rocket_stage_second.desc().localized(),
                        stage: rocket.secondStage
                    )
                    .padding([.horizontal], 32)
                    .padding([.top], 40)
                    
                    AppButton(
                        title: RocketsR.strings().rocket_show_launches_btn.desc().localized(),
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
                .customFont(.body1)
                .foregroundColor(.textTertiary)
            Spacer()
            Text(value)
                .customFont(.body1)
                .foregroundColor(.textPrimary)
        }
    }
}

struct RocketParamCell: View {
    let param: RocketParamUiModel
    
    var body: some View {
        VStack(spacing: 0) {
            Text(param.value)
                .customFont(.subtitle1)
                .foregroundColor(.textPrimary)
            Text(param.title.localized())
                .customFont(.body2)
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
                .customFont(.subtitle1)
                .textCase(.uppercase)
                .foregroundColor(.textPrimary)
                .frame(maxWidth: .infinity, alignment: .leading)
            stageInfoCell(
                title: RocketsR.strings().rocket_stage_engines_count.desc().localized(),
                value: stage.engines
            )
            stageInfoCell(
                title: RocketsR.strings().rocket_stage_fuel_amount.desc().localized(),
                value: stage.fuelAmountTons,
                unit: RocketsR.strings().rocket_stage_fuel_amount_unit.desc().localized()
            )
            stageInfoCell(
                title: RocketsR.strings().rocket_stage_burn_time.desc().localized(),
                value: stage.burnTimeSec,
                unit: RocketsR.strings().rocket_stage_burn_time_unit.desc().localized()
            )
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
                .customFont(.body1)
                .foregroundColor(.textTertiary)
            Spacer()
            Text(value)
                .customFont(.subtitle1)
                .foregroundColor(.textPrimary)
            Text(unit)
                .customFont(.subtitle1)
                .foregroundColor(.textSecondary)
        }
    }
}
