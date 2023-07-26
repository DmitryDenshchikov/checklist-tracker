plugins {
    `kotlin-dsl`
    kotlin("plugin.spring") version "1.8.20"
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    implementation("io.spring.dependency-management:io.spring.dependency-management.gradle.plugin:1.1.2")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.1.2")
    implementation("org.jetbrains.kotlin.plugin.spring:org.jetbrains.kotlin.plugin.spring.gradle.plugin:1.8.22")
}
