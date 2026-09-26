import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderConvertible
import org.gradle.kotlin.dsl.accessors.runtime.extensionOf
import org.gradle.plugin.use.PluginDependency

/** Pasang plugin dari version catalog. */
fun PluginManager.alias(notation: Provider<PluginDependency>) {
    apply(notation.get().pluginId)
}

/** Pasang plugin dari alias version catalog yang bisa dikonversi. */
fun PluginManager.alias(notation: ProviderConvertible<PluginDependency>) {
    apply(notation.asProvider().get().pluginId)
}

/** Jalankan konfigurasi setelah plugin yang diminta terpasang. */
fun PluginManager.withPlugin(
    notation: Provider<PluginDependency>,
    action: PluginManager.() -> Unit,
) {
    withPlugin(notation.get().pluginId) {
        action()
    }
}

/** Tambahkan dependency produksi. */
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

/** Tambahkan dependency khusus debug. */
fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

/** Tambahkan dependency unit test. */
fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

/** Ambil version catalog yang tersedia pada project ini. */
internal val Project.libs
    get(): LibrariesForLibs = extensionOf(this, "libs") as LibrariesForLibs
