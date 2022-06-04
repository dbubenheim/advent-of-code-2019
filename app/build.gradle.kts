import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.21")

    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("com.willowtreeapps.assertk:assertk:0.25")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}