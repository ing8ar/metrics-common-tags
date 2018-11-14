import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val springDependencyManagementVersion = "1.0.6.RELEASE"

    id("io.spring.dependency-management") version springDependencyManagementVersion
}

dependencyManagement.imports {
    val springBootVersion = "2.1.0.RELEASE"
    mavenBom("org.springframework.boot:spring-boot:$springBootVersion")
}

dependencies {
    implementation(project(":metrics-common-tags-core"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.springframework.boot:spring-boot")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation("org.springframework.boot:spring-boot-actuator-autoconfigure")
    implementation("io.micrometer:micrometer-core")

    kapt("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")
}