import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.coreNetwork)
            implementation(libs.decompose)
            implementation(libs.ktor.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.moko.resources)
        }
    }
}


multiplatformResources {
    resourcesPackage.set("ru.alexpanov.spacex.rockets")
    resourcesClassName.set("RocketsR")
}

android {
    namespace = "ru.alexpanov.rockets"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
}