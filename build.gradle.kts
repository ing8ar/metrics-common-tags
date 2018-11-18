import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginConvention
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    base
    kotlin("jvm") version "1.3.0" apply false
    id("java")
    id("org.jetbrains.dokka") version "0.9.17"
    id("jacoco")
    id("signing")
    id("maven")
}

allprojects {
    group = "io.github.ing8ar.metrics-common-tags"
    version = "0.1.0"

    repositories {
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/groups/staging")
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "jacoco")
    apply(plugin = "signing")
    apply(plugin = "maven")

    val javadocJar by tasks.creating(Jar::class) {
        classifier = "javadoc"
        from(tasks["dokka"])
    }
    val sourcesJar by tasks.creating(Jar::class) {
        classifier = "sources"
        from(sourceSets["main"].allSource)
    }

    tasks.withType<DokkaTask> {
        outputFormat = "javadoc"
        val javadoc: Javadoc by tasks
        outputDirectory = javadoc.destinationDir!!.path
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<Test> {
        finalizedBy("jacocoTestReport")
    }

    artifacts {
        add("archives", javadocJar)
        add("archives", sourcesJar)
    }

    signing {
        useGpgCmd()
        sign(configurations.archives)
    }

    tasks {
        "uploadArchives"(Upload::class) {
            repositories {
                withConvention(MavenRepositoryHandlerConvention::class) {
                    mavenDeployer {
                        beforeDeployment {
                            signing.signPom(this)
                        }
                        withGroovyBuilder {
                            "repository"("url" to uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")) {
                                "authentication"(
                                    "userName" to project.properties["ossrhUsername"],
                                    "password" to project.properties["ossrhPassword"]
                                )
                            }
                            "snapshotRepository"("url" to "https://oss.sonatype.org/content/repositories/snapshots") {
                                "authentication"(
                                    "userName" to project.properties["ossrhUsername"],
                                    "password" to project.properties["ossrhPassword"]
                                )

                            }
                        }
                        pom.project {
                            withGroovyBuilder {
                                "name"("${project.name}")
                                "description"("Micro-util for adding common tags to Spring Boot metrics (micrometer)")
                                "packaging"("jar")
                                "url"("https://github.com/ing8ar/metrics-common-tags")
                                "scm" {
                                    "connection"("scm:git:git@github.com:ing8ar/metrics-common-tags.git")
                                    "developerConnection"("scm:git:git@github.com:ing8ar/metrics-common-tags.git")
                                    "url"("https://github.com/ing8ar/metrics-common-tags")
                                }
                                "licenses" {
                                    "license" {
                                        "name"("The Apache Software License, Version 2.0")
                                        "url"("http://www.apache.org/licenses/LICENSE-2.0.txt")
                                        "distribution"("repo")
                                    }
                                }
                                "developers" {
                                    "developer" {
                                        "id"("ing8ar")
                                        "name"("ing8ar")
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

dependencies {
    // Make the root project archives configuration depend on every sub-project
    subprojects.forEach {
        archives(it)
    }
}