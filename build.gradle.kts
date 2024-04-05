plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("dgroomes.MainKt")
}

dependencies {
    implementation(platform(libs.http4k.bom))
    implementation(libs.http4k.core)
    implementation(libs.http4k.netty)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.slf4j.simple)
}
