plugins {
    alias(libs.plugins.odyctivity.android.library)
}

android {
    namespace = "com.sedate.app.odyctivity.telemetry"
}

dependencies {
    implementation(project(":odyctivity-core"))

    implementation(platform(libs.opentelemetry.android.bom))
    implementation(libs.opentelemetry.android.agent)
}