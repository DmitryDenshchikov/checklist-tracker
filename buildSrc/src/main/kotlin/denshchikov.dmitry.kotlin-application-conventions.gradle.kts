plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
    kotlin("jvm")
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