import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

dependencies {
    val kloggingVersion = "1.6.20"
    val junit5Version = "5.3.1"
    val mockkVersion = "1.8.9"
    val assertjVersion = "3.11.1"

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("io.github.microutils:kotlin-logging:$kloggingVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5Version")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit5Version")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")

    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
}