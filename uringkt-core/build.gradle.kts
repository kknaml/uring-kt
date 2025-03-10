plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    explicitApi()

    sourceSets {
        main {
            dependencies {
                api(project(":uringkt-binding"))
                api(libs.kotlinx.coroutines.core)
            }
        }
    }
}