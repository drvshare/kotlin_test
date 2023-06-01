rootProject.name = "kotlin_test"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings

        kotlin("jvm") version kotlinVersion apply false
    }
}

include("m3oop")
include("m4dsl")
include("m5coroutines")
include("m6-channels")
