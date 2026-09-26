package com.sedate.app.odyctivity.security

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import java.util.WeakHashMap

/** Kelola overlay mengikuti fokus window dan lifecycle Activity. */
internal class OdyRecentPrivacyController(
    private val config: OdyRecentPrivacy.Overlay,
    private val screenCaptureProtection: OdyScreenCaptureProtection,
) {
    private val focusListeners =
        WeakHashMap<Activity, ViewTreeObserver.OnWindowFocusChangeListener>()

    /** Pasang listener fokus sekali per Activity; kehilangan fokus juga bisa terjadi di luar Recents. */
    fun install(activity: Activity) {
        if (focusListeners.containsKey(activity)) return

        val listener = ViewTreeObserver.OnWindowFocusChangeListener { hasFocus ->
            if (hasFocus) hide(activity) else show(activity)
        }

        activity.window.decorView.viewTreeObserver.addOnWindowFocusChangeListener(listener)
        focusListeners[activity] = listener
    }

    /** Pasang overlay jika belum ada, lalu tangguhkan proteksi screenshot. */
    fun show(activity: Activity) {
        val decorView = activity.window.decorView as? ViewGroup ?: return
        val existingOverlay = decorView.findViewById<View>(OdyRecentPrivacyOverlay.OVERLAY_ID)

        if (existingOverlay == null) {
            val overlay = OdyRecentPrivacyOverlay.create(
                context = activity,
                config = config,
            )

            decorView.addView(
                overlay,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                ),
            )
        }

        // Tambahkan overlay sebelum melepas flag; waktu pengambilan preview tetap diatur sistem.
        screenCaptureProtection.suspendForRecents(activity.window)
    }

    /** Pulihkan proteksi screenshot sebelum melepas overlay. */
    fun hide(activity: Activity) {
        val decorView = activity.window.decorView as? ViewGroup ?: return
        val overlay = decorView.findViewById<View>(OdyRecentPrivacyOverlay.OVERLAY_ID)

        // Pulihkan proteksi sebelum konten screen ditampilkan lagi.
        screenCaptureProtection.resumeAfterRecents(activity.window)

        if (overlay != null) {
            decorView.removeView(overlay)
        }
    }

    /** Lepas listener dan overlay saat Activity selesai dipakai. */
    fun uninstall(activity: Activity) {
        focusListeners.remove(activity)?.let { listener ->
            val observer = activity.window.decorView.viewTreeObserver
            if (observer.isAlive) {
                observer.removeOnWindowFocusChangeListener(listener)
            }
        }

        hide(activity)
    }
}
