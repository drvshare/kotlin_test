plugins {
    kotlin("jvm") apply false
    application
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version
}


application {
    mainClass.set("MainKt")
}
