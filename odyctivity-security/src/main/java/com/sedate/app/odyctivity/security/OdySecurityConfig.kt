package com.sedate.app.odyctivity.security

/** Pengaturan privasi Recents; proteksi screenshot per-screen diatur terpisah. */
data class OdySecurityConfig(
    val recentPrivacy: OdyRecentPrivacy = OdyRecentPrivacy.Disabled,
)
