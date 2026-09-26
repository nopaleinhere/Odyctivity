package com.sedate.app.odyctivity.core

import com.sedate.app.odyctivity.core.plugin.OdyctivityPlugin

/** Susun konfigurasi plugin sebelum runtime dimulai. */
class OdyctivityConfigBuilder internal constructor() {
    private val plugins = mutableListOf<OdyctivityPlugin>()

    /** Tambahkan satu plugin ke konfigurasi. */
    fun install(plugin: OdyctivityPlugin) {
        plugins += plugin
    }

    /** Buat snapshot plugin agar builder tidak mengubah konfigurasi jadi. */
    internal fun build(): OdyctivityConfig {
        return OdyctivityConfig(plugins = plugins.toList())
    }
}

/** Buat konfigurasi Odyctivity dengan DSL singkat. */
fun odyctivityConfig(block: OdyctivityConfigBuilder.() -> Unit): OdyctivityConfig {
    return OdyctivityConfigBuilder()
        .apply(block)
        .build()
}
