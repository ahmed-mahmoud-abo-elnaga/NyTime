import com.nytimes.buildsrc.dependencies.Dependencies
import com.nytimes.buildsrc.dependencies.ProjectModules
import com.nytimes.buildsrc.dependencies.TestDependencies

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.nytimes.buildsrc.android.plugin")
    id("dagger.hilt.android.plugin")
    id("shot")
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

androidPlugin {
    buildType = com.nytimes.buildsrc.android.plugin.BuildType.App
}

android {
    defaultConfig {
        applicationId = "com.nytimes.example"
        minSdk = com.nytimes.buildsrc.dependencies.AndroidSettings.minSdk
        targetSdk = com.nytimes.buildsrc.dependencies.AndroidSettings.targetSdk
        testInstrumentationRunner =
            "com.nytimes.example.webmock.MockAndShotTestRunner"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isTestCoverageEnabled = true
            buildConfigField("Integer", "PORT", "8080")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }
    }
}

dependencies {
    implementation(project(ProjectModules.api))
    implementation(project(ProjectModules.domain))
    implementation(project(ProjectModules.data))

    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.lifecycleLivedataKtx)
    implementation(Dependencies.AndroidX.Compose.viewModel)
    kapt(Dependencies.AndroidX.lifecycleCompiler)
    implementation(Dependencies.AndroidX.archComponents)
    implementation(Dependencies.AndroidX.browser)

    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.systemUiController)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.uiTooling)
    implementation(Dependencies.AndroidX.Compose.runtime)
    implementation(Dependencies.AndroidX.Compose.runtimeLiveData)
    implementation(Dependencies.AndroidX.Compose.navigation)

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)
    implementation(Dependencies.Hilt.hiltCompiler)
    implementation(Dependencies.Hilt.hiltNavigationCompose)

    implementation(Dependencies.coilCompose)
    implementation(Dependencies.lottie)
    implementation(Dependencies.lottieCompose)

    implementation(Dependencies.AndroidX.constraintlayout)
    implementation(Dependencies.AndroidX.legacySupport)
    implementation(Dependencies.AndroidX.Navigation.fragmentKtx)
    implementation(Dependencies.AndroidX.Navigation.uiKtx)

    testImplementation(project(ProjectModules.coreAndroidTest))
    testImplementation(project(ProjectModules.domain))

    androidTestImplementation(project(ProjectModules.coreAndroidTest))
    androidTestImplementation(TestDependencies.AndroidX.core)
    androidTestImplementation(TestDependencies.AndroidX.coreKtx)
    androidTestImplementation(TestDependencies.AndroidX.runner)
    androidTestImplementation(TestDependencies.AndroidX.rules)
    androidTestImplementation(TestDependencies.AndroidX.composeUiTest)
    androidTestImplementation(TestDependencies.AndroidX.composeUiTestJUnit4)
    debugImplementation(TestDependencies.AndroidX.uiTestManifest)


    androidTestImplementation(TestDependencies.Hilt.androidTesting)
    kaptAndroidTest(TestDependencies.Hilt.androidCompiler)
    androidTestAnnotationProcessor(TestDependencies.Hilt.androidCompiler)
}
