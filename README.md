# Odyctivity

Library Android modular untuk lifecycle aplikasi, logging, privasi layar Recents, dan telemetry. Modul `sample` menunjukkan cara memasangnya.

## Pakai dari proyek lain

Tambahkan JitPack di `settings.gradle.kts` proyek pemakai:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Tambahkan modul yang dibutuhkan di `build.gradle.kts` aplikasi:

```kotlin
dependencies {
    implementation("com.github.nopaleinhere.Odyctivity:odyctivity-core:v0.1.0")
    implementation("com.github.nopaleinhere.Odyctivity:odyctivity-logging:v0.1.0")
    implementation("com.github.nopaleinhere.Odyctivity:odyctivity-security:v0.1.0")
    // Opsional: implementation("com.github.nopaleinhere.Odyctivity:odyctivity-telemetry:v0.1.0")
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

Publikasikan tag Git, lalu buka `https://jitpack.io/#nopaleinhere/Odyctivity` untuk memicu dan memeriksa build rilis.
