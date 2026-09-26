package com.sedate.app.odyctivity.core

import com.sedate.app.odyctivity.core.plugin.OdyctivityPlugin

/** Daftar plugin yang dipasang ketika runtime dimulai. */
data class OdyctivityConfig(
    val plugins: List<OdyctivityPlugin> = emptyList(),
)
