rootProject.name = "SpaceXRockets"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":composeApp")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
include(":root")
include(":rockets")
include(":launches")
include(":settings")
include(":core-network")
include(":core")
