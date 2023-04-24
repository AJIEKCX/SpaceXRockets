import ComposeApp

func mutableValue<T: AnyObject>(
  _ initialValue: T
) -> MutableValue<T> {
  MutableValueBuilderKt.MutableValue(
    initialValue: initialValue
  ) as! MutableValue<T>
}
