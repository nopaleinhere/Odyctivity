# Odyctivity

Library Android modular untuk lifecycle aplikasi, logging, privasi layar Recents, dan telemetry. Modul `sample` menunjukkan cara memasangnya.

## Pakai dari proyek lain

Setelah repository publik diberi tag rilis, tambahkan JitPack di `settings.gradle.kts` proyek pemakai:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Tambahkan modul yang dibutuhkan di `build.gradle.kts` aplikasi. Ganti `OWNER` dengan nama akun GitHub dan `VERSION` dengan tag rilis, misalnya `v0.1.0`:

```kotlin
dependencies {
    implementation("com.github.OWNER.Odyctivity:odyctivity-core:VERSION")
    implementation("com.github.OWNER.Odyctivity:odyctivity-logging:VERSION")
    implementation("com.github.OWNER.Odyctivity:odyctivity-security:VERSION")
    // Opsional: implementation("com.github.OWNER.Odyctivity:odyctivity-telemetry:VERSION")
}
```

Modul logging dan security membawa dependency `odyctivity-core` secara transitif. Koordinat di atas tersedia setelah JitPack berhasil membangun tag tersebut.

## Konfigurasi

Daftarkan plugin di `Application`:

```kotlin
Odyctivity.initialize(
    application = this,
    config = odyctivityConfig {
        install(OdyLoggingPlugin())
        install(
            OdySecurityPlugin(
                OdySecurityConfig(
                    recentPrivacy = OdyRecentPrivacy.Overlay(
                        title = "Content Protected",
                        imageRes = R.drawable.your_logo,
                    ),
                ),
            ),
        )
    },
)
```

Lihat [`sample/src/main/java/com/sedate/app/odyctivity/sample/SampleApplication.kt`](sample/src/main/java/com/sedate/app/odyctivity/sample/SampleApplication.kt) untuk contoh lengkap. Untuk proteksi screenshot per screen, simpan instance `OdySecurityPlugin` yang dipasang lalu gunakan `screenCaptureProtection.enable(window)` ketika screen sensitif masuk dan `disable(window)` saat keluar. Panggil keduanya pada main thread dan pasangkan jumlah pemanggilannya.

## Rilis

Keempat modul library memakai `maven-publish` dan menerbitkan varian `release` beserta source jar. JitPack membaca `GROUP`, `ARTIFACT`, dan `VERSION` untuk koordinat rilis. Sebelum memberi tag, jalankan:

```bash
./gradlew build publishToMavenLocal
```

Publikasikan tag Git, lalu buka `https://jitpack.io/#OWNER/Odyctivity` untuk memicu dan memeriksa build rilis.
