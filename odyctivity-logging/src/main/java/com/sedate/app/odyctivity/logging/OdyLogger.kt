package com.sedate.app.odyctivity.logging

/** Tujuan log yang bisa diganti atau ditambah lewat dependency injection. */
fun interface OdyLogger {
    /** Terima satu entri log. */
    fun log(entry: OdyLogEntry)
}
