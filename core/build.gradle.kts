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
            implementation(libs.decompose)
            implementation(libs.kotlinx.coroutines.core)
            api(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "ru.alexpanov.core"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    dependencies {
        coreLibraryDesugaring(libs.desugarJdkLibs)
    }
}