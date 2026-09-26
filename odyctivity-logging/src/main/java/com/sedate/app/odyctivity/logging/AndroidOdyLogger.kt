package com.sedate.app.odyctivity.logging

import android.util.Log

/** Tulis entri log ke Android Logcat. */
class AndroidOdyLogger : OdyLogger {
    /** Pilih level Logcat dan tulis pesan yang sudah diformat. */
    override fun log(entry: OdyLogEntry) {
        val tag = entry.tag ?: DEFAULT_TAG

        val message = buildMessage(entry)

        when (entry.level) {
            OdyLogLevel.VERBOSE -> {
                Log.v(tag, message, entry.throwable)
            }

            OdyLogLevel.DEBUG -> {
                Log.d(tag, message, entry.throwable)
            }

            OdyLogLevel.INFO -> {
                Log.i(tag, message, entry.throwable)
            }

            OdyLogLevel.WARN -> {
                Log.w(tag, message, entry.throwable)
            }

            OdyLogLevel.ERROR -> {
                Log.e(tag, message, entry.throwable)
            }
        }
    }

    /** Tambahkan metadata ke pesan jika tersedia. */
    private fun buildMessage(entry: OdyLogEntry): String {
        if (entry.metadata.isEmpty()) return entry.message

        val metadata = entry.metadata.entries.joinToString(separator = ", ") { (key, value) ->
            "$key=$value"
        }

        return "${entry.message} | $metadata"
    }

    private companion object {
        const val DEFAULT_TAG = "Odyctivity"
    }
}
