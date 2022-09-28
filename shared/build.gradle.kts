plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    val ktorVersion = "1.6.5"
    val serializationVersion = "1.3.1"
    val sqlDelightVersion = "1.5.3"
    val coroutinesVersion = "1.6.0-native-mt"
    val loggingVersion = "2.1.0"

    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64() //sure all ios dependencies support this target
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        /**
         * here we add the dependencies required by the main module
         * */
        val commonMain by getting {
            dependencies {
                /**
                 * Logger
                 * */
                implementation("io.github.aakira:napier:$loggingVersion")

                /**
                 * Ktor
                 * */
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")

                /**
                 * coroutines
                 * */
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                /**
                 * dbSqlDelight
                 * */
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
                implementation ("com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        /**
         * here we add the dependencies required by the android main module
         * */
        val androidMain by getting {
            dependencies {
                /**
                 * ktor
                 * */
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

                /**
                 * coroutines
                 * */
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

                /**
                 * db SqlDelight
                 * */
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")

            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        /**
         * here we add the dependencies required by the ios main module
         * */
        val iosX64Main by getting {
            dependencies {
                /**
                 * ktor
                 * */
                implementation("io.ktor:ktor-client-ios:$ktorVersion")

                /**
                 * db SqlDelight
                 * */
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val iosArm64Main by getting {
            dependencies {
                /**
                 * ktor
                 * */
                implementation("io.ktor:ktor-client-ios:$ktorVersion")

                /**
                 * db SqlDelight
                 * */
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val iosSimulatorArm64Main by getting {
            dependencies {
                /**
                 * ktor
                 * */
                implementation("io.ktor:ktor-client-ios:$ktorVersion")

                /**
                 * db SqlDelight
                 * */
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}

sqldelight {
    database("CurrencyDb") {
        packageName = "com.example.weatherappkmm.db"
    }
}