plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.minaMikhail.home"
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

    viewBinding.isEnabled = true

    lint {
        quiet = true
        abortOnError = false
        warningsAsErrors = true
        disable += "Instantiatable"
    }
}

dependencies {

    // Support
    implementation(libs.bundles.support)
    implementation(libs.bundles.archComponents)

    // Kotlin
    implementation(kotlin("reflect"))
    implementation(libs.bundles.kotlinCoroutines)

    // UI
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    // Core Modules
    implementation(projects.core.base)
    implementation(projects.core.network)
    implementation(projects.core.preferences)
    implementation(projects.core.utils)
}