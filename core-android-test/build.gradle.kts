import com.nytimes.buildsrc.dependencies.Dependencies
import com.nytimes.buildsrc.dependencies.TestDependencies

plugins {
    id("com.android.library")
    id("com.nytimes.buildsrc.android.plugin")
}

dependencies {
    implementation(TestDependencies.kotlinxCoroutines)
    implementation(Dependencies.AndroidX.Navigation.fragmentKtx)
    implementation(TestDependencies.AndroidX.espressoCore)
    implementation(TestDependencies.AndroidX.espressoContrib)

}