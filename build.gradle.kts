// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("com.facebook.react") version "0.81.3" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        libs.hilt
        libs.hiltCompiler
        libs.react.native.gradle.plugin
    }
}
dependencies {
    libs.hilt
    libs.hiltCompiler
    libs.react.native.gradle.plugin
}
