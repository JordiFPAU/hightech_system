plugins {
    id("java")
    id("io.quarkus") version "3.35.2"
    id("io.freefair.lombok") version "9.2.0"

}

group = "com.hightech"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25

}

val quarkusVersion = "3.35.2"
repositories {
    mavenCentral()
}

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusVersion"))
    // CDI
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-rest:3.36.0")
    implementation("io.quarkus:quarkus-hibernate-orm:3.36.0")
    implementation("io.quarkus:quarkus-rest-jackson:3.36.0")
    implementation("io.quarkus:quarkus-hibernate-orm-panache:3.36.0")
    implementation("io.quarkus:quarkus-smallrye-jwt:3.36.0")
    implementation("io.quarkus:quarkus-smallrye-jwt-build:3.36.0")
    implementation("io.quarkus:quarkus-hibernate-validator:3.36.0")
    implementation("io.quarkus:quarkus-smallrye-openapi:3.36.0")
    implementation("io.quarkus:quarkus-jdbc-postgresql:3.36.0")
    implementation("io.quarkus:quarkus-elytron-security-common")

}

tasks.test {
    useJUnitPlatform()
}