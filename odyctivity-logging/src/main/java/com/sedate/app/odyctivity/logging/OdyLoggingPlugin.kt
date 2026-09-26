package com.sedate.app.odyctivity.logging

import android.app.Activity
import android.app.Application
import com.sedate.app.odyctivity.core.plugin.OdyctivityPlugin

/** Daftarkan logger dan catat lifecycle bila diaktifkan. */
class OdyLoggingPlugin(
    private val loggers: List<OdyLogger> = listOf(AndroidOdyLogger()),
    private val config: OdyLoggingConfig = OdyLoggingConfig(),
) : OdyctivityPlugin {

    /** Masukkan logger yang disuntikkan ke penyalur log. */
    override fun install(application: Application) {
        loggers.forEach(OdyLog::addLogger)
    }

    /** Catat aplikasi masuk foreground. */
    override fun onAppForeground(activity: Activity) {
        if (!config.lifecycleLoggingEnabled) return

        OdyLog.d(
            message = "Foreground",
            tag = APP_TAG,
            metadata = mapOf("activity" to activity::class.java.simpleName),
        )
    }

    /** Catat aplikasi masuk background. */
    override fun onAppBackground(activity: Activity) {
        if (!config.lifecycleLoggingEnabled) return

        OdyLog.d(
            message = "Background",
            tag = APP_TAG,
            metadata = mapOf("activity" to activity.javaClass.simpleName),
        )
    }

    /** Catat pembuatan Activity. */
    override fun onActivityCreated(activity: Activity) {
        lifecycle(activity, "onCreate")
    }

    /** Catat Activity mulai terlihat. */
    override fun onActivityStarted(activity: Activity) {
        lifecycle(activity, "onStart")
    }

    /** Catat Activity aktif. */
    override fun onActivityResumed(activity: Activity) {
        lifecycle(activity, "onResume")
    }

    /** Catat Activity dijeda. */
    override fun onActivityPaused(activity: Activity) {
        lifecycle(activity, "onPause")
    }

    /** Catat Activity berhenti terlihat. */
    override fun onActivityStopped(activity: Activity) {
        lifecycle(activity, "onStop")
    }

    /** Catat penghancuran Activity. */
    override fun onActivityDestroyed(activity: Activity) {
        lifecycle(activity, "onDestroy")
    }

    /** Tulis satu peristiwa Activity jika logging lifecycle aktif. */
    private fun lifecycle(
        activity: Activity,
        event: String,
    ) {
        if (!config.lifecycleLoggingEnabled) return

        OdyLog.d(
            message = event,
            tag = ACTIVITY_TAG,
            metadata = mapOf("activity" to activity::class.java.simpleName),
        )
    }

    private companion object {
        const val APP_TAG = "OdyApplication"
        const val ACTIVITY_TAG = "OdyLifecycle"
    }
}
