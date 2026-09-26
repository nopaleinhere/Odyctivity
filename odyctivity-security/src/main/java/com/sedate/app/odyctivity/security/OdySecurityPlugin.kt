package com.sedate.app.odyctivity.security

import android.app.Activity
import com.sedate.app.odyctivity.core.plugin.OdyctivityPlugin

/** Hubungkan privasi Recents dengan lifecycle Activity dan sediakan proteksi per-screen. */
class OdySecurityPlugin(
    private val config: OdySecurityConfig = OdySecurityConfig(),
    /** Instance bersama untuk proteksi per-screen dan overlay Recents; gunakan di main thread. */
    val screenCaptureProtection: OdyScreenCaptureProtection = OdyWindowScreenCaptureProtection(),
) : OdyctivityPlugin {

    private val recentPrivacyController =
        (config.recentPrivacy as? OdyRecentPrivacy.Overlay)?.let { overlay ->
            OdyRecentPrivacyController(
                config = overlay,
                screenCaptureProtection = screenCaptureProtection,
            )
        }

    /** Siapkan pemantauan fokus ketika Activity dibuat. */
    override fun onActivityCreated(activity: Activity) {
        recentPrivacyController?.install(activity)
    }

    /** Tampilkan overlay saat Activity dijeda sebagai fallback listener fokus. */
    override fun onActivityPaused(activity: Activity) {
        recentPrivacyController?.show(activity)
    }

    /** Pulihkan proteksi dan sembunyikan overlay ketika Activity aktif lagi. */
    override fun onActivityResumed(activity: Activity) {
        recentPrivacyController?.hide(activity)
    }

    /** Bersihkan listener dan overlay ketika Activity dihancurkan. */
    override fun onActivityDestroyed(activity: Activity) {
        recentPrivacyController?.uninstall(activity)
    }
}
