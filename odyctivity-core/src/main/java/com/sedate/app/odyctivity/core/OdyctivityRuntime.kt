package com.sedate.app.odyctivity.core

import android.app.Activity
import android.app.Application
import com.sedate.app.odyctivity.core.lifecycle.OdyActivityLifecycleCallbacks
import com.sedate.app.odyctivity.core.lifecycle.OdyApplicationState

/** Simpan state aplikasi dan teruskan lifecycle ke plugin yang terpasang. */
internal object OdyctivityRuntime {
    private var config: OdyctivityConfig = OdyctivityConfig()
    private var initialized = false
    private var startedActivityCount = 0
    private var applicationState = OdyApplicationState.BACKGROUND

    /** Pasang plugin dan callback lifecycle satu kali. */
    fun initialize(
        application: Application,
        config: OdyctivityConfig,
    ) {
        if (initialized) return

        this.config = config

        config.plugins.forEach { plugin ->
            plugin.install(application)
        }

        application.registerActivityLifecycleCallbacks(createActivityLifecycleCallbacks())

        initialized = true
    }

    /** Hubungkan callback Android ke fungsi runtime. */
    private fun createActivityLifecycleCallbacks() = OdyActivityLifecycleCallbacks(
        onCreated = ::onActivityCreated,
        onStarted = ::onActivityStarted,
        onResumed = ::onActivityResumed,
        onPaused = ::onActivityPaused,
        onStopped = ::onActivityStopped,
        onDestroyed = ::onActivityDestroyed,
    )

    /** Beri tahu plugin bahwa Activity baru dibuat. */
    private fun onActivityCreated(activity: Activity) {
        config.plugins.forEach { plugin ->
            plugin.onActivityCreated(activity)
        }
    }

    /** Hitung Activity terlihat dan beri tahu plugin saat aplikasi masuk foreground. */
    private fun onActivityStarted(activity: Activity) {
        startedActivityCount++

        config.plugins.forEach { plugin ->
            plugin.onActivityStarted(activity)
        }

        if (startedActivityCount == 1 && applicationState == OdyApplicationState.BACKGROUND) {
            applicationState = OdyApplicationState.FOREGROUND

            config.plugins.forEach { plugin ->
                plugin.onAppForeground(activity)
            }
        }
    }

    /** Beri tahu plugin bahwa Activity kembali aktif. */
    private fun onActivityResumed(activity: Activity) {
        config.plugins.forEach { plugin ->
            plugin.onActivityResumed(activity)
        }
    }

    /** Beri tahu plugin saat Activity dijeda, dengan urutan terbalik. */
    private fun onActivityPaused(activity: Activity) {
        config.plugins
            .asReversed()
            .forEach { plugin ->
                plugin.onActivityPaused(activity)
            }
    }

    /** Kurangi Activity terlihat dan beri tahu plugin saat aplikasi masuk background. */
    private fun onActivityStopped(activity: Activity) {
        config.plugins
            .asReversed()
            .forEach { plugin ->
                plugin.onActivityStopped(activity)
            }

        startedActivityCount = (startedActivityCount - 1).coerceAtLeast(0)

        if (activity.isChangingConfigurations) return

        if (startedActivityCount == 0 && applicationState == OdyApplicationState.FOREGROUND) {
            applicationState = OdyApplicationState.BACKGROUND

            config.plugins
                .asReversed()
                .forEach { plugin ->
                    plugin.onAppBackground(activity)
                }
        }
    }

    /** Beri tahu plugin bahwa Activity dihancurkan. */
    private fun onActivityDestroyed(activity: Activity) {
        config.plugins
            .asReversed()
            .forEach { plugin ->
                plugin.onActivityDestroyed(activity)
            }
    }
}
