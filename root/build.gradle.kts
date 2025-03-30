plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("kotlin-parcelize")
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
            implementation(projects.coreKoin)
            implementation(projects.coreNetwork)
            implementation(projects.rockets)
            implementation(projects.launches)
            implementation(projects.settings)
            implementation(libs.decompose)
            implementation(libs.koin.core)
            implementation(libs.ktor.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "ru.alexpanov.root"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
}