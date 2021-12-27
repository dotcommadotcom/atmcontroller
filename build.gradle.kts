plugins {
    java
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("junit:junit:4.12")
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    runtimeOnly("org.junit.platform:junit-platform-console:1.7.1")
}

tasks {
    val flags = listOf("-Xlint:unchecked", "-Xlint:deprecation", "-Werror")

    getByName<JavaCompile>("compileJava") {
        options.compilerArgs = flags
    }

    getByName<JavaCompile>("compileTestJava") {
        options.compilerArgs = flags
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform {}
}

tasks.withType<JacocoReport> {
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude("*/preview/*")
            }
        }))
    }
}

defaultTasks("clean", "test", "jacocoTestReport")

jacoco {
    toolVersion = "0.8.6"
}
