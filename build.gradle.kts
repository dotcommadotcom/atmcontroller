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

val test by tasks.getting(Test::class) {
  useJUnitPlatform {}
  finalizedBy(tasks.jacocoTestReport)
  doLast {
    println("View code coverage at:")
    println("file://$buildDir/reports/tests/test/index.html")
  }
}

tasks.jacocoTestReport {
  dependsOn(tasks.test)
  reports {
    xml.required.set(false)
    csv.required.set(false)
  }
}


jacoco {
  toolVersion = "0.8.7"
}

defaultTasks("clean", "test", "jacocoTestReport")