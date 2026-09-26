import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
/** Siapkan Compose dan dependency UI untuk modul Android. */
class AndroidComposeConventionPlugin : Plugin<Project> {
    /** Aktifkan Compose pada aplikasi atau library yang memakai plugin ini. */
    override fun apply(target: Project) {
        with(target) {
            pluginManager.alias(libs.plugins.kotlin.compose)

            pluginManager.withPlugin(libs.plugins.android.application) {
                extensions.configure<ApplicationExtension> {
                    buildFeatures {
                        compose = true
                    }
                }
            }

            pluginManager.withPlugin(libs.plugins.android.library) {
                extensions.configure<LibraryExtension> {
                    buildFeatures {
                        compose = true
                    }
                }
            }

            dependencies {
                implementation(platform(libs.androidx.compose.bom))
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.compose.foundation)
                implementation(libs.androidx.compose.material3)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.compose.ui)
                implementation(libs.androidx.compose.ui.tooling.preview)
                debugImplementation(libs.androidx.compose.ui.tooling)
            }
        }
    }
}
