import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf

plugins {
    `kotlin-dsl`
}

group = "com.sedate.app.odyctivity.buildlogic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    implementation(libs.kotlin.compose.compiler.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)

    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}


gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "odyctivity.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "odyctivity.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidCompose") {
            id = "odyctivity.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
    }
}
