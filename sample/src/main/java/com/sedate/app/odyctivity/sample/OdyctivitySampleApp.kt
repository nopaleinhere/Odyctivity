package com.sedate.app.odyctivity.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/** Tampilkan contoh konten yang perlu disembunyikan di preview Recents. */
@Composable
fun OdyctivitySampleApp() {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Sensitive Information",
                    style = MaterialTheme.typography.headlineMedium,
                )

                Spacer(Modifier.height(16.dp))
                Text("Account: 123456789")
                Text("Balance: Rp 100.000.000")
            }
        }
    }
}
