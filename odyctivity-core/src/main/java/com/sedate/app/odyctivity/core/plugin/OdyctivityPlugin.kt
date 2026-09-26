package com.sedate.app.odyctivity.core.plugin

import android.app.Activity
import android.app.Application

/** Hook opsional untuk mengikuti lifecycle aplikasi dan Activity. */
interface OdyctivityPlugin {
    /** Dipanggil sekali ketika plugin didaftarkan. */
    fun install(application: Application) = Unit
    /** Dipanggil ketika Activity pertama mulai berjalan. */
    fun onAppForeground(activity: Activity) = Unit
    /** Dipanggil ketika tidak ada Activity yang berjalan. */
    fun onAppBackground(activity: Activity) = Unit
    /** Dipanggil setelah Activity dibuat. */
    fun onActivityCreated(activity: Activity) = Unit
    /** Dipanggil ketika Activity mulai terlihat. */
    fun onActivityStarted(activity: Activity) = Unit
    /** Dipanggil ketika Activity aktif. */
    fun onActivityResumed(activity: Activity) = Unit
    /** Dipanggil ketika Activity dijeda. */
    fun onActivityPaused(activity: Activity) = Unit
    /** Dipanggil ketika Activity berhenti terlihat. */
    fun onActivityStopped(activity: Activity) = Unit
    /** Dipanggil sebelum Activity selesai dibersihkan. */
    fun onActivityDestroyed(activity: Activity) = Unit
}
