plugins {
    alias(libs.plugins.odyctivity.android.library)
}

android {
    namespace = "com.sedate.app.odyctivity.security"
}

dependencies {
    implementation(project(":odyctivity-core"))
}
