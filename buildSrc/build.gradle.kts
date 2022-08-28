repositories {
    jcenter()
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")

    implementation(gradleApi())
}