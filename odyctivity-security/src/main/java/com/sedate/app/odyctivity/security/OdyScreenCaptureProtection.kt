package com.sedate.app.odyctivity.security

import android.view.Window

/**
 * Kontrak proteksi screenshot dan rekam layar. Panggil lewat main thread.
 * Gunakan instance bersama dari [OdySecurityPlugin.screenCaptureProtection].
 */
abstract class OdyScreenCaptureProtection {
    /** Tambah satu permintaan proteksi; pasangkan dengan satu [disable] pada window yang sama. */
    abstract fun enable(window: Window)

    /** Lepas satu permintaan; proteksi tetap aktif jika masih ada pemakai lain. */
    abstract fun disable(window: Window)

    /** Cek flag proteksi saat ini; nilainya bisa nonaktif sementara saat overlay Recents tampil. */
    abstract fun isEnabled(window: Window): Boolean

    /** Tangguhkan proteksi sesudah overlay Recents menutup konten. */
    abstract fun suspendForRecents(window: Window)

    /** Pulihkan proteksi sebelum overlay Recents dilepas. */
    abstract fun resumeAfterRecents(window: Window)
}
