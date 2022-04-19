plugins {
    `java-library`
    `maven-publish`
    `jacoco`
    id("org.barfuin.gradle.jacocolog") version "2.0.0"
    id("io.freefair.lombok") version "6.4.2"
    id("com.diffplug.spotless") version "6.4.2"
}

description = "Library to retrieve Maven POM information including there referenced dependencies."
group = "org.spliffone"
version = "0.1.0-SNAPSHOT"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

java {
    targetCompatibility = JavaVersion.VERSION_1_8
    sourceCompatibility = JavaVersion.VERSION_1_8

    withJavadocJar()
    withSourcesJar()
}

dependencies {

    // Logging facade
    implementation("org.slf4j:slf4j-api:1.7.36")

    // XML Parser
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.2.2")

    // Apache 2.0
    // https://search.maven.org/artifact/com.google.code.findbugs/jsr305
    api("com.google.code.findbugs:jsr305:3.0.2")

    // HTTP client
    implementation("org.apache.httpcomponents.client5:httpclient5:5.1.3")

    // Builder and utils to simplify our life
    implementation("org.apache.commons:commons-lang3:3.12.0")

    // Enable logging framework during tests
    testImplementation("ch.qos.logback:logback-classic:1.2.11")

    // Additional utils
    testImplementation("org.apache.commons:commons-io:1.3.2")

    testImplementation("ch.qos.logback:logback-core:1.2.11")

    // Rich and fluent assertions for testing for Java
    testImplementation("org.assertj:assertj-core:3.22.0")
}

spotless {
    java {
        importOrder("java", "javax", "com.acme", "", "\\#org.spliffone", "\\#")
        removeUnusedImports()
        googleJavaFormat() // has its own section below
        eclipse()          // has its own section below
    }
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter("5.8.1")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                url.set("https://github.com/spliffone/maven-pom-crawler")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("raykb")
                        name.set("Rayk Bajohr")
                        email.set("rayk.bajohr@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:spliffone/maven-pom-crawler.git")
                    developerConnection.set("scm:git@github.com:spliffone/maven-pom-crawler.git")
                    url.set("https://github.com/spliffone/maven-pom-crawler")
                }
            }

            from(components["java"])
        }
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.6".toBigDecimal()
            }
        }
    }
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}