plugins {
    id("java")
}

group = "tools.redstone"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        url = uri("https://libraries.minecraft.net")
    }
}

dependencies {
    implementation("com.mojang:brigadier:1.0.18")
    implementation("org.projectlombok:lombok:1.18.28")
    implementation("org.jetbrains:annotations:24.0.1")

    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

tasks.test {
    useJUnitPlatform()
}