plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.minaMikhail.networkCaching"
    compileSdk = rootProject.extra.get("compileSdk") as Int

    defaultConfig {
        applicationId = rootProject.extra.get("applicationId") as String
        minSdk = rootProject.extra.get("minSdk") as Int
        targetSdk = rootProject.extra.get("compileSdk") as Int
        versionCode = rootProject.extra.get("versionCode") as Int
        versionName = rootProject.extra.get("versionName") as String

        testInstrumentationRunner = rootProject.extra.get("testRunner") as String
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false

            proguardFiles(
                getDefaultProguardFile(rootProject.extra.get("defaultProguardFileName") as String),
                rootProject.extra.get("proguardFileName") as String
            )
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

    // Feature Modules
    implementation(projects.features.home)
}