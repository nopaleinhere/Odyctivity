import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
/** Terapkan konfigurasi Android dan dependency dasar untuk modul aplikasi. */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    /** Pasang plugin Android lalu atur SDK dan dependency pengujian. */
    override fun apply(target: Project) {
        with(target) {
            pluginManager.alias(libs.plugins.android.application)

            extensions.configure<ApplicationExtension> {
                configureAndroid(this)
            }

            dependencies {
                implementation(libs.material)
                testImplementation(libs.junit4)
            }
        }
    }
}
