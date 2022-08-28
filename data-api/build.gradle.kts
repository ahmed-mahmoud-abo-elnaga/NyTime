import com.nytimes.buildsrc.dependencies.Dependencies
import com.nytimes.buildsrc.dependencies.ProjectModules

plugins {
    id("com.android.library")
    id("com.nytimes.buildsrc.android.plugin")
}

dependencies {
    implementation(project(ProjectModules.data))
    testImplementation(project(ProjectModules.coreAndroidTest))

    api(Dependencies.Retrofit.retrofit)
    api(Dependencies.Retrofit.retrofitConverterGson)
    api(Dependencies.okHttpLoggingInterceptor)

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)

}