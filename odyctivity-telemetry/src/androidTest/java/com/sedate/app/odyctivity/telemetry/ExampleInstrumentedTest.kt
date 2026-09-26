package com.sedate.app.odyctivity.telemetry

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.assertEquals

/**
 * Contoh test instrumentasi untuk memastikan package aplikasi tersedia di perangkat.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    /** Pastikan package context sesuai dengan modul yang diuji. */
    @Test
    fun useAppContext() {
        // Ambil context aplikasi yang sedang diuji.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.sedate.app.odyctivity.telemetry", appContext.packageName)
    }
}
