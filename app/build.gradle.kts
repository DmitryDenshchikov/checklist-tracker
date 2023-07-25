plugins {
    id("denshchikov.dmitry.kotlin-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
}

application {
    mainClass.set("denshchikov.dmitry.app.AppKt")
}
