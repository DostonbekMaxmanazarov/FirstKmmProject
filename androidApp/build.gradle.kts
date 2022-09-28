import org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument.DefaultArgument.arguments

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.example.weatherappkmm.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    /**
     * new added
     * Kotlin coroutine
     * */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    implementation ( "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")


}