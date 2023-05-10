
plugins {
    id("jtemplate.android.application")
    id("jtemplate.android.application.compose")
    id("jtemplate.android.hilt")
}

android {
    defaultConfig {
        applicationId = "se.joeldenke.jtemplate"
        versionCode = 5
        versionName = "0.0.5" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    namespace = "se.joeldenke.jtemplate"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":feature:login"))
    implementation(project(":feature:search"))

    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.accompanist.testharness)
    debugImplementation(libs.androidx.compose.ui.testManifest)

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.coil.kt)
}