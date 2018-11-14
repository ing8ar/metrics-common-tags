import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    base
    kotlin("jvm") version "1.3.0" apply false
    id("org.jetbrains.dokka") version "0.9.17"
    id("jacoco")
}

allprojects {
    group = "io.github.ing8ar.metrics-common-tags"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "kotlin")
    apply(plugin = "jacoco")

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<Test> {
        finalizedBy("jacocoTestReport")
    }
}

dependencies {
    // Make the root project archives configuration depend on every sub-project
    subprojects.forEach {
        archives(it)
    }
}
