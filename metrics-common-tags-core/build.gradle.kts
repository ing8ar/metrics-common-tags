import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

dependencies {
    val kloggingVersion = "1.6.20"
    val junit5Version = "5.3.1"
    val mockkVersion = "1.8.9"
    val assertjVersion = "3.11.1"

    api(kotlin("stdlib-jdk8"))
    api(kotlin("reflect"))

    implementation("io.github.microutils:kotlin-logging:$kloggingVersion")

    testApi("org.junit.jupiter:junit-jupiter-api:$junit5Version")
    testApi("org.junit.jupiter:junit-jupiter-params:$junit5Version")
    testApi("io.mockk:mockk:$mockkVersion")
    testApi("org.assertj:assertj-core:$assertjVersion")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
}