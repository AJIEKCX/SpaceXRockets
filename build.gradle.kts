plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.cocoapods).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
    alias(libs.plugins.sqlDelight).apply(false)
    alias(libs.plugins.composeCompiler).apply(false)
}

buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        val mokoResourceVersion = libs.versions.mokoResources.get()
        classpath("dev.icerock.moko:resources-generator:$mokoResourceVersion")
    }
}