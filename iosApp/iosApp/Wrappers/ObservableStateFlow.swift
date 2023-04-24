import ComposeApp
import SwiftUI

final class ObservableStateFlow<T: AnyObject>: ObservableObject {
  private let observableFlow: AnyStateFlow<T>
  @Published var value: T
  private var observer: Cancellable?
  
  init(_ flow: AnyStateFlow<T>) {
    observableFlow = flow
    self.value = flow.value
    observer = observableFlow.collect(
      onEach: { [weak self] value in self?.value = value }
    )
  }
  
  deinit {
    observer?.cancel()
  }
}
