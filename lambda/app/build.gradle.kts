plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "finance.insights"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
    implementation("software.amazon.awssdk:bedrockruntime:2.25.5")
}

application {
    mainClass.set("finance.insights.App")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "finance.insights.App"
    }

    // Include all dependencies in the jar
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}