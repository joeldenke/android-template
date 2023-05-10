plugins {
    id("jtemplate.android.feature")
    id("jtemplate.android.library.compose")
}

android {
    namespace = "se.joeldenke.jtemplate.feature.search"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}