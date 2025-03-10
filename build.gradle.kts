plugins {
    alias(libs.plugins.kotlin.jvm) apply false
}

group = "kknaml"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}
