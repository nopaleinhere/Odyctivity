package com.sedate.app.odyctivity.security

import android.view.Window
import android.view.WindowManager
import java.util.WeakHashMap

/** Kelola FLAG_SECURE dan jumlah pemakai proteksi untuk tiap window. */
internal class OdyWindowScreenCaptureProtection : OdyScreenCaptureProtection() {
    /** Simpan jumlah pemakai dan status flag sebelum controller menyentuh window. */
    private data class WindowState(
        var users: Int,
        val wasSecure: Boolean,
        var suspended: Boolean = false,
    )

    private val states = WeakHashMap<Window, WindowState>()

    /** Tambah pemakai proteksi, lalu sesuaikan flag window. */
    override fun enable(window: Window) {
        val state = states.getOrPut(window) {
            WindowState(users = 0, wasSecure = isEnabled(window))
        }
        state.users++
        updateWindowFlag(window, state)
    }

    /** Kurangi pemakai proteksi; pemakai lain tetap mempertahankan proteksinya. */
    override fun disable(window: Window) {
        val state = states[window] ?: return
        if (state.users == 0) return

        state.users--
        updateWindowFlag(window, state)
        if (state.users == 0 && !state.suspended) states.remove(window)
    }

    /** Baca status FLAG_SECURE langsung dari window. */
    override fun isEnabled(window: Window): Boolean =
        window.attributes.flags and WindowManager.LayoutParams.FLAG_SECURE != 0

    /** Tangguhkan flag tanpa menghapus jumlah pemakai proteksi. */
    override fun suspendForRecents(window: Window) {
        val state = states.getOrPut(window) {
            WindowState(users = 0, wasSecure = isEnabled(window))
        }
        if (state.suspended) return

        state.suspended = true
        updateWindowFlag(window, state)
    }

    /** Pulihkan flag sesuai pemakai aktif atau kondisi awal window. */
    override fun resumeAfterRecents(window: Window) {
        val state = states[window] ?: return
        if (!state.suspended) return

        state.suspended = false
        updateWindowFlag(window, state)
        if (state.users == 0) states.remove(window)
    }

    /** Pertahankan flag awal saat semua pemakai selesai. */
    private fun updateWindowFlag(window: Window, state: WindowState) {
        val shouldBeSecure = !state.suspended && (state.users > 0 || state.wasSecure)

        if (shouldBeSecure) {
            window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        }
    }
}
