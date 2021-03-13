val kotlinReactVer: String by project
val kotlinReactRouterVer: String by project
val kotlinReactTableVer: String by project

plugins {
    kotlin("js") version "1.4.31"
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
}

kotlin {
    js(IR) {
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