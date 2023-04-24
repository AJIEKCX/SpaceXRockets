import ComposeApp

func simpleChildStack<T: AnyObject>(
  _ child: T
) -> Value<ChildStack<AnyObject, T>> {
  mutableValue(
    ChildStack(
      configuration: "config" as AnyObject,
      instance: child
    )
  )
}
