plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
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
            implementation(libs.koin.core)
            implementation(libs.decompose)
        }
    }
}

android {
    namespace = "ru.alexpanov.core_koin"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
}