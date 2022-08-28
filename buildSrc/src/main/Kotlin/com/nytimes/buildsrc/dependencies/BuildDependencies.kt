package com.nytimes.buildsrc.dependencies

object BuildDependencies {
    const val androidGradle =
        "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltAndroidGradlePlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val karumiShotGradlePlugin = "com.karumi:shot:${Versions.karumiShot}"
}