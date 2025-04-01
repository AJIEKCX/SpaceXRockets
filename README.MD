# SpaceXRockets

[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.10-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-EAEAEA.svg?style=flat)

A KMP sample showing the SpaceX rockets and launches using [SpaceXApi](https://github.com/r-spacex/SpaceX-API)

<img src="https://github.com/AJIEKCX/SpaceXRockets/assets/18354891/00415a10-0370-4552-8680-a9c33350cb26" width="720">

## The stack
- [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) and [SwiftUI](https://developer.apple.com/xcode/swiftui/)
- [Ktor](https://github.com/ktorio/ktor)
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
- [Kotlinx Datetime](https://github.com/Kotlin/kotlinx-datetime)
- [Decompose](https://github.com/arkivanov/Decompose)

## Showcase

### Android

https://github.com/AJIEKCX/SpaceXRockets/assets/18354891/9c325a2e-0dc3-4a2c-a896-7c9e7111ddf1

### iOS
https://github.com/AJIEKCX/SpaceXRockets/assets/18354891/03130744-067c-43b3-bc2a-151aeee517f8

## Before running!
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 8 on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there
 - run `./gradlew podInstall` in the project root

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :composeApp:assembleDebug`
 - find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcworkspace` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio
