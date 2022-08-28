import com.nytimes.buildsrc.dependencies.Dependencies
import com.nytimes.buildsrc.dependencies.ProjectModules

plugins {
    id("com.android.library")
    id("com.nytimes.buildsrc.android.plugin")
}

dependencies {
    implementation(project(ProjectModules.domain))
    testImplementation(project(ProjectModules.coreAndroidTest))

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)

    implementation(Dependencies.jodaTime)
    testImplementation(Dependencies.jodaTime)
}