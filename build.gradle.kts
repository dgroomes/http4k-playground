plugins {
    kotlin("jvm") version "1.7.20" // Latest Gradle Kotlin plugin releases: https://kotlinlang.org/docs/gradle.html
    application
}

val slf4jVersion = "1.7.36" // SLF4J releases: http://www.slf4j.org/news.html
val http4kVersion = "4.32.2.0" // http4K releases: https://github.com/http4k/http4k/releases

repositories {
    mavenCentral()
}

application {
    mainClass.set("dgroomes.MainKt")
}

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    runtimeOnly("org.slf4j:slf4j-simple:$slf4jVersion")

    implementation(platform("org.http4k:http4k-bom:$http4kVersion"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-netty")
}
