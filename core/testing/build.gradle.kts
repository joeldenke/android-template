plugins {
    id("jtemplate.android.library")
    id("jtemplate.android.library.compose")
    id("jtemplate.android.hilt")
}

android {
    namespace = "se.joeldenke.jtemplate.core.testing"
}

dependencies {
    api(libs.androidx.compose.ui.test)
    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)

    debugApi(libs.androidx.compose.ui.testManifest)
}
