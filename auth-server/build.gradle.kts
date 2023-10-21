import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.9.10"

    // jetbrains
    kotlin("plugin.spring") version kotlinVersion
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion

    // spring
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

val postgresqlVersion by extra("42.6.0")

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-authorization-server")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Database
    implementation("org.postgresql:postgresql:$postgresqlVersion")

    // JetBrains
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    enabled = false
}