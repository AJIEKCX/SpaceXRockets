import ComposeApp
import SwiftUI

public class ObservableValue<T : AnyObject> : ObservableObject {
  @Published
  public var value: T

  private var cancellation: Cancellation?

  public init(_ value: Value<T>) {
    self.value = value.value
    self.cancellation = value.subscribe { [weak self] value in self?.value = value }
  }

  deinit {
    cancellation?.cancel()
  }
}
