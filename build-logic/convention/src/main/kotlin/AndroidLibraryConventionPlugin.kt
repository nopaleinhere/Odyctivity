import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

@Suppress("unused")
/** Terapkan konfigurasi Android dan dependency dasar untuk modul library. */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    /** Siapkan Android, Kotlin, dan dependency pengujian. */
    override fun apply(target: Project) {
        with(target) {
            pluginManager.alias(libs.plugins.android.library)
            pluginManager.apply("maven-publish")

            group = System.getenv("GROUP")
                ?.let { owner -> System.getenv("ARTIFACT")?.let { repository -> "$owner.$repository" } }
                ?: "com.sedate.app.odyctivity"
            version = System.getenv("VERSION") ?: "0.1.0-SNAPSHOT"

            extensions.configure<KotlinAndroidProjectExtension> {
                jvmToolchain(17)
            }

            extensions.configure<LibraryExtension> {
                configureAndroid(this)

                publishing {
                    singleVariant("release") {
                        withSourcesJar()
                    }
                }
            }

            afterEvaluate {
                extensions.configure<PublishingExtension> {
                    publications {
                        register<MavenPublication>("release") {
                            from(components.getByName("release"))
                        }
                    }
                }
            }

            dependencies {
                implementation(libs.material)
                testImplementation(libs.junit4)
            }
        }
    }
}
