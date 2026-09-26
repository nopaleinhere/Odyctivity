package com.sedate.app.odyctivity.core

import android.app.Application
import androidx.activity.ComponentActivity

/** Activity dasar yang menyediakan akses awal ke runtime Odyctivity. */
abstract class Odyctivity : ComponentActivity() {
    companion object {
        /** Pasang plugin sekali saat Application mulai berjalan. */
        fun initialize(
            application: Application,
            config: OdyctivityConfig = OdyctivityConfig(),
        ) {
            OdyctivityRuntime.initialize(
                application = application,
                config = config,
            )
        }
    }
}
