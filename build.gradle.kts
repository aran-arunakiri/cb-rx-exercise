import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "mc-techsessions"
version = "1.0-SNAPSHOT"
plugins {
    java
    kotlin("jvm") version "1.3.41"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.reactivex.rxjava2:rxjava:2.2.13")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation(kotlin("stdlib-jdk8"))
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}