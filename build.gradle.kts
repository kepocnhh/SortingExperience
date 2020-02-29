buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

plugins {
    id("org.gradle.application")
    id("org.jetbrains.kotlin.jvm") version "1.3.61"
}

application {
    mainClassName = "sorting.AppKt"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.61")
}
