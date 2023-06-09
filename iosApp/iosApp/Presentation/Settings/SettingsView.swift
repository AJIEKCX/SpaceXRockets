import SwiftUI
import ComposeApp

struct SettingsView: View {
    let component: SettingsComponent
    
    @ObservedObject private var observableState: ObservableStateFlow<RocketSettings>
    
    private var state: RocketSettings { observableState.value }
    
    init(_ component: SettingsComponent) {
        self.component = component
        observableState = .init(component.state)
        
        UISegmentedControl.appearance().selectedSegmentTintColor = UIColor(Color.textPrimary)
        UISegmentedControl.appearance().backgroundColor = UIColor(Color.cardPrimary)
        UISegmentedControl.appearance().setTitleTextAttributes([.foregroundColor: UIColor(Color.background)], for: .selected)
        UISegmentedControl.appearance().setTitleTextAttributes([.foregroundColor: UIColor(Color.textSecondary)], for: .normal)
    }
    
    var body: some View {
        NavigationView {
            content
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .principal) {
                    Text(SettingsR.strings().settings_title.desc().localized())
                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: component.onDismissClick) {
                        Text(SettingsR.strings().settings_close_btn.desc().localized())
                            .bold()
                    }
                    .foregroundColor(.textPrimary)
                }
            }
        }
    }
    
    var content: some View {
        VStack {
            SettingsCell(
                title: SettingsR.strings().settings_height.desc().localized(),
                picker: {
                    DistanceUnitPicker(
                        unit: state.height,
                        onValueChanged: component.onHeightChanged
                    )
                }
            )
            SettingsCell(
                title: SettingsR.strings().settings_diameter.desc().localized(),
                picker: {
                    DistanceUnitPicker(
                        unit: state.diameter,
                        onValueChanged: component.onDiameterChanged
                    )
                }
            )
            SettingsCell(
                title: SettingsR.strings().settings_mass.desc().localized(),
                picker: {
                    MassUnitPicker(
                        unit: state.mass,
                        onValueChanged: component.onMassChanged
                    )
                }
            )
            SettingsCell(
                title: SettingsR.strings().settings_payload_weight.desc().localized(),
                picker: {
                    MassUnitPicker(
                        unit: state.payloadWeight,
                        onValueChanged: component.onPayloadWeightChanged
                    )
                }
            )
        }
        .padding([.vertical], 56)
        .padding([.horizontal], 24)
        .frame(minHeight: 0, maxHeight: .infinity, alignment: .top)
    }
}

private struct SettingsCell<PickerContent: SettingsPicker>: View {
    let title: String
    let picker: PickerContent
    
    init(title: String, @ViewBuilder picker: () -> PickerContent) {
        self.title = title
        self.picker = picker()
    }
    
    var body: some View {
        HStack {
            Text(title)
                .customFont(.body1)
                .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
            picker
                .frame(width: 115, height: 40)
        }
        .pickerStyle(.segmented)
    }
}

private protocol SettingsPicker: View {}

private struct MassUnitPicker: SettingsPicker {
    let unit: MassUnit
    let onValueChanged: (MassUnit) -> Void
    
    var body: some View {
        Picker(
            "mass_unit",
            selection: Binding(get: { unit }, set: onValueChanged),
            content: {
                Text("kg").tag(MassUnit.kg)
                Text("lb").tag(MassUnit.lb)
            }
        )
    }
}

private struct DistanceUnitPicker: SettingsPicker {
    let unit: DistanceUnit
    let onValueChanged: (DistanceUnit) -> Void
    
    var body: some View {
        Picker(
            "distance_unit",
            selection: Binding(get: { unit }, set: onValueChanged),
            content: {
                Text("m").tag(DistanceUnit.meters)
                Text("ft").tag(DistanceUnit.feet)
            }
        )
    }
}
