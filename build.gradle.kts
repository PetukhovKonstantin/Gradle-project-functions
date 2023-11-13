plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("jacoco")
}

group = "ru.netology"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("junit:junit:4.13.1")
}

tasks.test {
    useJUnit()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}