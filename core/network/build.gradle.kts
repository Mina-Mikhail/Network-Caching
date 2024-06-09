plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.minaMikhail.network"
    compileSdk = rootProject.extra.get("compileSdk") as Int

    defaultConfig {
        minSdk = rootProject.extra.get("minSdk") as Int
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            consumerProguardFiles(rootProject.extra.get("proguardFileName") as String)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions.jvmTarget = rootProject.extra.get("jvmVersion") as String

    buildFeatures.buildConfig = true

    lint {
        quiet = true
        abortOnError = false
        warningsAsErrors = true
        disable += "Instantiatable"
    }
}

dependencies {

    // Kotlin
    implementation(kotlin("reflect"))

    // Networking
    api(libs.bundles.networking)

    // Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}