package com.sedate.app.odyctivity.security

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/** Pembuat overlay privasi berisi warna latar, gambar, dan judul opsional. */
internal object OdyRecentPrivacyOverlay {
    internal const val OVERLAY_ID = 0x0D1C7101

    /** Buat view penutup layar dengan gambar dan judul di tengah. */
    fun create(
        context: Context,
        config: OdyRecentPrivacy.Overlay,
    ): View = FrameLayout(context).apply {
        id = OVERLAY_ID
        setBackgroundColor(config.backgroundColor)

        isClickable = true
        isFocusable = true

        if (config.imageRes != null || config.title != null) {
            addView(
                LinearLayout(context).apply {
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER

                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        Gravity.CENTER,
                    )

                    config.imageRes?.let { imageRes ->
                        addView(
                            ImageView(context).apply {
                                setImageResource(imageRes)
                                contentDescription = config.imageContentDescription
                                adjustViewBounds = true
                                scaleType = ImageView.ScaleType.CENTER_INSIDE

                                val imageSize = config.imageSizeDp.dp(context)
                                layoutParams = LinearLayout.LayoutParams(imageSize, imageSize).apply {
                                    if (config.title != null) bottomMargin = 16.dp(context)
                                }
                            },
                        )
                    }

                    config.title?.let { title ->
                        addView(
                            TextView(context).apply {
                                text = title
                                setTextColor(Color.WHITE)
                                textSize = 18f
                                gravity = Gravity.CENTER
                                isSingleLine = true
                            },
                        )
                    }
                },
            )
        }
    }

    /** Ubah ukuran dp menjadi piksel sesuai kepadatan layar. */
    private fun Int.dp(context: Context): Int =
        (this * context.resources.displayMetrics.density).toInt()
}
