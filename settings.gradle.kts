pluginManagement {
    includeBuild("../TwilioSMSTest/node_modules/@react-native/gradle-plugin")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("$rootDir/../node_modules/react-native/android") }
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.google.dagger")) {
                useModule("com.google.dagger:hilt-android-gradle-plugin:${requested.version}")
            }
        }
    }
    plugins {
        id("dagger.hilt.android.plugin") version "2.48.1" apply false
    }
}

plugins { id("com.facebook.react.settings") }

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("$rootDir/../node_modules/react-native/android") }
    }
}

includeBuild("../TwilioSMSTest/node_modules/@react-native/gradle-plugin")
rootProject.name = "TwilioSMSTest"
include(":app")
