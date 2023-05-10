plugins {
    id("jtemplate.android.library")
    id("jtemplate.android.library.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        checkDependencies = true
    }
    namespace = "se.joeldenke.jtemplate.core.designsystem"
}

dependencies {
    //lintPublish(project(":lint"))

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    /*
    implementation "com.airbnb.android:showkase:1.0.0-beta18"
    ksp "com.airbnb.android:showkase-processor:1.0.0-beta18"
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation 'androidx.core:core-ktx:1.10.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.1'
    implementation 'androidx.graphics:graphics-core:1.0.0-alpha03'
    implementation 'androidx.graphics:graphics-shapes:1.0.0-alpha02'
    implementation 'androidx.activity:activity-compose:1.7.1'
    def composeBom = platform('androidx.compose:compose-bom:2023.04.01')
    implementation composeBom
    androidTestImplementation composeBom
    implementation 'androidx.compose.ui:ui'
    implementation 'androidx.compose.foundation:foundation'
    implementation 'androidx.compose.ui:ui-graphics'
    implementation 'androidx.compose.ui:ui-tooling-preview'
    implementation 'androidx.core:core-ktx:1.10.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
     */

    debugApi(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.graphics.core)
    implementation(libs.androidx.graphics.shapes)


    //androidTestImplementation(project(":core:testing"))
}