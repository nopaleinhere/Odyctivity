package com.sedate.app.odyctivity.logging

/** Salurkan pesan ke semua logger yang terdaftar. */
object OdyLog {
    private val loggers = mutableSetOf<OdyLogger>()

    /** Daftarkan tujuan log; instance yang sama tidak diduplikasi. */
    fun addLogger(logger: OdyLogger) {
        loggers += logger
    }

    /** Hentikan pengiriman log ke logger ini. */
    fun removeLogger(logger: OdyLogger) {
        loggers -= logger
    }

    /** Hapus semua logger yang terdaftar. */
    fun clearLoggers() {
        loggers.clear()
    }

    /** Kirim pesan verbose. */
    fun v(
        message: String,
        tag: String? = null,
        metadata: Map<String, Any?> = emptyMap(),
    ) {
        log(
            level = OdyLogLevel.VERBOSE,
            message = message,
            tag = tag,
            metadata = metadata,
        )
    }

    /** Kirim pesan debug. */
    fun d(
        message: String,
        tag: String? = null,
        metadata: Map<String, Any?> = emptyMap(),
    ) {
        log(
            level = OdyLogLevel.DEBUG,
            message = message,
            tag = tag,
            metadata = metadata,
        )
    }

    /** Kirim pesan informasi. */
    fun i(
        message: String,
        tag: String? = null,
        metadata: Map<String, Any?> = emptyMap(),
    ) {
        log(
            level = OdyLogLevel.INFO,
            message = message,
            tag = tag,
            metadata = metadata,
        )
    }

    /** Kirim pesan peringatan beserta error jika ada. */
    fun w(
        message: String,
        tag: String? = null,
        throwable: Throwable? = null,
        metadata: Map<String, Any?> = emptyMap(),
    ) {
        log(
            level = OdyLogLevel.WARN,
            message = message,
            tag = tag,
            throwable = throwable,
            metadata = metadata,
        )
    }

    /** Kirim pesan error beserta penyebabnya jika ada. */
    fun e(
        message: String,
        tag: String? = null,
        throwable: Throwable? = null,
        metadata: Map<String, Any?> = emptyMap(),
    ) {
        log(
            level = OdyLogLevel.ERROR,
            message = message,
            tag = tag,
            throwable = throwable,
            metadata = metadata,
        )
    }

    /** Bentuk satu entri lalu kirim ke semua logger. */
    fun log(
        level: OdyLogLevel,
        message: String,
        tag: String? = null,
        throwable: Throwable? = null,
        metadata: Map<String, Any?> = emptyMap(),
    ) {
        val entry = OdyLogEntry(
            level = level,
            message = message,
            tag = tag,
            throwable = throwable,
            metadata = metadata,
        )

        loggers.forEach { logger ->
            logger.log(entry)
        }
    }
}
