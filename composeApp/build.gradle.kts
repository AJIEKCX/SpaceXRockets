plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqlDelight)
    id("org.jetbrains.kotlin.plugin.compose")
    id("dev.icerock.mobile.multiplatform-resources")
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

    cocoapods {
        version = "1.0.0"
        summary = "Compose application framework"
        homepage = "empty"
        ios.deploymentTarget = "15.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "ComposeApp"
            isStatic = true
            export(libs.decompose)
            export(libs.essenty)
            export(libs.moko.resources)
            export(projects.core)
            export(projects.root)
            export(projects.rockets)
            export(projects.launches)
            export(projects.settings)
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.root)
            implementation(projects.rockets)
            implementation(projects.launches)
            implementation(projects.settings)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.composeImageLoader)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.multiplatformSettings)
            implementation(libs.koin.core)
            implementation(libs.coil.compose)
            implementation(libs.coil.ktor3)
            api(libs.moko.resources.compose)
            api(libs.moko.resources)
            api(libs.decompose)
            api(libs.essenty)
        }

        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activityCompose)
            implementation(libs.compose.uitooling)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.sqlDelight.driver.android)
            implementation(libs.decompose.compose)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqlDelight.driver.native)
            api(projects.core)
            api(projects.root)
            api(projects.rockets)
            api(projects.launches)
            api(projects.settings)
        }
    }
}

multiplatformResources {
    resourcesPackage.set("ru.alexpanov.spacex")
}

android {
    namespace = "ru.alexpanov.spacex"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 35

        applicationId = "ru.alexpanov.spacex.androidApp"
        versionCode = 1
        versionName = "1.0.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packaging {
        resources.excludes.add("META-INF/**")
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    dependencies {
        coreLibraryDesugaring(libs.desugarJdkLibs)
    }
}


sqldelight {
    databases {
        create("MyDatabase") {
            // Database configuration here.
            // https://cashapp.github.io/sqldelight
            packageName.set("ru.alexpanov.spacex.db")
        }
    }
}
