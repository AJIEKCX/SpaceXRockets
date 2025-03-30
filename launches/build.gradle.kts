plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.coreNetwork)
            implementation(projects.coreKoin)
            implementation(libs.decompose)
            implementation(libs.koin.core)
            implementation(libs.ktor.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "ru.alexpanov.launches"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
}