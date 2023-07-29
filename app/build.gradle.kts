import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("denshchikov.dmitry.kotlin-application-conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.flywaydb:flyway-core:9.21.1")
    implementation("org.postgresql:postgresql:42.6.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

application {
    mainClass.set("denshchikov.dmitry.app.AppKt")
}
