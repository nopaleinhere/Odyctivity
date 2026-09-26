plugins {
    alias(libs.plugins.odyctivity.android.library)
}

android {
    namespace = "com.sedate.app.odyctivity.logging"
}

dependencies {
    implementation(project(":odyctivity-core"))
}