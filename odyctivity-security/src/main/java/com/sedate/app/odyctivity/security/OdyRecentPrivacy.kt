package com.sedate.app.odyctivity.security

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

/** Pilihan tampilan privasi saat aplikasi masuk Recents. */
sealed interface OdyRecentPrivacy {
    /** Biarkan preview aplikasi tampil tanpa overlay privasi. */
    data object Disabled : OdyRecentPrivacy

    /** Atur warna latar, gambar opsional berukuran dp, dan judul overlay Recents. */
    data class Overlay(
        @param:ColorInt val backgroundColor: Int = Color.BLACK,
        @param:DrawableRes val imageRes: Int? = null,
        val imageContentDescription: String? = null,
        val imageSizeDp: Int = 96,
        val title: String? = null,
    ) : OdyRecentPrivacy
}
