import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val springDependencyManagementVersion = "1.0.6.RELEASE"

    id("io.spring.dependency-management") version springDependencyManagementVersion
}
version = "0.1.1"

dependencyManagement.imports {
    val springBootVersion = "2.1.0.RELEASE"
    mavenBom("org.springframework.boot:spring-boot:$springBootVersion")
}

dependencies {
    api(kotlin("stdlib-jdk8"))

    implementation("io.github.ing8ar.metrics-common-tags:metrics-common-tags-core:0.1.0")

    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation("org.springframework.boot:spring-boot-actuator-autoconfigure")
    implementation("io.micrometer:micrometer-core")

    kapt("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")
}