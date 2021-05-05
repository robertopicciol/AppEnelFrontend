
val kotlinReactVer: String by project
val kotlinReactRouterVer: String by project
val kotlinReactTableVer: String by project
val kotlinStyledVer : String by project
val rechartsVer : String by project

plugins {
    kotlin("js") version "1.5.0"
}

group = "com.roby66one.enel.js"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
}

dependencies {
    testImplementation(kotlin("test-js"))
    implementation("org.jetbrains:kotlin-react:${kotlinReactVer}")
    implementation("org.jetbrains:kotlin-react-dom:${kotlinReactVer}")
    implementation("org.jetbrains:kotlin-react-router-dom:${kotlinReactRouterVer}")
    implementation("org.jetbrains:kotlin-react-table:${kotlinReactTableVer}")
    implementation("org.jetbrains:kotlin-styled:${kotlinStyledVer}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-RC")
    implementation(npm("recharts", rechartsVer))
}

kotlin {
    js(LEGACY) {
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
}