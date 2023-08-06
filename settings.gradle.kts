plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.6.0"
}

rootProject.name = "checklist-tracker"
include("app")
include("auth-server")
