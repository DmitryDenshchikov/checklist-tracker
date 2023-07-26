plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")

    application
}

repositories {
    mavenCentral()
}

dependencies {

}

tasks.named<Test>("test") {
    useJUnitPlatform()
}