package com.sedate.app.odyctivity.logging

/** Satu pesan log beserta level, error, dan metadata opsional. */
data class OdyLogEntry(
    val level: OdyLogLevel,
    val message: String,
    val tag: String? = null,
    val throwable: Throwable? = null,
    val metadata: Map<String, Any?> = emptyMap(),
)
