plugins {
    alias(libs.plugins.odyctivity.android.application)
    alias(libs.plugins.odyctivity.android.compose)
}

android {
    namespace = "com.sedate.app.odyctivity.sample"

    defaultConfig {
        applicationId = "com.sedate.app.odyctivity.sample"

        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":odyctivity-core"))
    implementation(project(":odyctivity-logging"))
    implementation(project(":odyctivity-security"))
    implementation(project(":odyctivity-telemetry"))
}