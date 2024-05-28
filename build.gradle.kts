import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

tasks.wrapper {
    gradleVersion = "8.7"
}

plugins {
    kotlin("jvm") version "1.9.23"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
    mavenLocal()
}

group = "io.github.animatedledstrip"
version = "1.0"
description = "An AnimatedLEDStrip server for Raspberry Pis"

val animatedledstripServerVersion: String? by project
val alsServerVersion = animatedledstripServerVersion ?: "1.1.2"

sourceSets.main {
    dependencies {
        implementation("io.github.animatedledstrip:animatedledstrip-server-jvm:$alsServerVersion")
        implementation("com.github.mbelling:rpi-ws281x-java:2.0.0-SNAPSHOT")
        api("org.apache.logging.log4j:log4j-core:2.23.1")
        api("org.apache.logging.log4j:log4j-api:2.23.1")
    }

    java.srcDirs("src/main/kotlin")
}

tasks.withType<ShadowJar> {
    manifest.attributes.apply {
        put("Main-Class", "animatedledstrip.server.example.MainKt")
    }
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

kotlin {
    jvmToolchain(8)
}
