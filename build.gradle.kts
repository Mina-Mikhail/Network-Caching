plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
}

buildscript {
    apply("config.gradle.kts")
}

tasks.register("clean", Delete::class) {
    description = "Remove build directory during clean task"
    group = JavaBasePlugin.DOCUMENTATION_GROUP

    delete(rootProject.layout.buildDirectory)
}