package com.sedate.app.odyctivity.core.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle

/** Teruskan callback Activity ke runtime tanpa menyimpan Activity. */
internal class OdyActivityLifecycleCallbacks(
    private val onCreated: (Activity) -> Unit,
    private val onStarted: (Activity) -> Unit,
    private val onResumed: (Activity) -> Unit,
    private val onPaused: (Activity) -> Unit,
    private val onStopped: (Activity) -> Unit,
    private val onDestroyed: (Activity) -> Unit,
) : Application.ActivityLifecycleCallbacks {
    /** Teruskan peristiwa pembuatan Activity. */
    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?,
    ) {
        onCreated(activity)
    }

    /** Teruskan peristiwa Activity mulai terlihat. */
    override fun onActivityStarted(activity: Activity) {
        onStarted(activity)
    }

    /** Teruskan peristiwa Activity aktif. */
    override fun onActivityResumed(activity: Activity) {
        onResumed(activity)
    }

    /** Teruskan peristiwa Activity dijeda. */
    override fun onActivityPaused(activity: Activity) {
        onPaused(activity)
    }

    /** Teruskan peristiwa Activity berhenti terlihat. */
    override fun onActivityStopped(activity: Activity) {
        onStopped(activity)
    }

    /** Teruskan peristiwa penghancuran Activity. */
    override fun onActivityDestroyed(activity: Activity) {
        onDestroyed(activity)
    }

    /** Callback wajib yang belum dipakai oleh runtime. */
    override fun onActivitySaveInstanceState(
        activity: Activity,
        outState: Bundle,
    ) = Unit
}
