plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    sourceSets {
        main {
            dependencies {
                api(project(":uringkt-core"))
            }
        }
    }
}