package com.sedate.app.odyctivity.sample

import android.app.Application
import android.graphics.Color
import com.sedate.app.odyctivity.core.Odyctivity
import com.sedate.app.odyctivity.core.odyctivityConfig
import com.sedate.app.odyctivity.logging.OdyLoggingPlugin
import com.sedate.app.odyctivity.security.OdyRecentPrivacy
import com.sedate.app.odyctivity.security.OdySecurityConfig
import com.sedate.app.odyctivity.security.OdySecurityPlugin

/** Daftarkan plugin logging dan security saat sample dimulai. */
class SampleApplication : Application() {
    /** Siapkan runtime sekali sebelum Activity dibuka. */
    override fun onCreate() {
        super.onCreate()

        Odyctivity.initialize(
            application = this,
            config = odyctivityConfig {
                install(OdyLoggingPlugin())
                install(
                    OdySecurityPlugin(
                        config = OdySecurityConfig(
                            recentPrivacy = OdyRecentPrivacy.Overlay(
                                backgroundColor = Color.rgb(24, 24, 27),
                                imageRes = R.mipmap.ic_launcher,
                                imageContentDescription = "Odyctivity",
                                title = "Content Protected",
                            ),
                        ),
                    ),
                )
            },
        )
    }
}
