package com.sedate.app.odyctivity.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import com.sedate.app.odyctivity.core.Odyctivity

/** Activity tunggal yang menampilkan UI Compose sample. */
class MainActivity : Odyctivity() {
    /** Pasang konten sample ketika Activity dibuat. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OdyctivitySampleApp()
        }
    }
}
