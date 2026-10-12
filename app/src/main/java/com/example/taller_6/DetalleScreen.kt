package com.example.taller_6

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetalleScreen(elemento: Elemento, onBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            when (elemento.id) {
                1 -> "🚀"
                2 -> "💊"
                3 -> "💍"
                4 -> "🪄"
                5 -> "🤵"
                6 -> "🏃"
                7 -> "🦸"
                8 -> "🦇"
                9 -> "🧸"
                10 -> "💀"
                11 -> "🦖"
                12 -> "🏺"
                else -> "🎬"
            },
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            elemento.titulo,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            elemento.categoria,
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(Modifier.height(16.dp))

        Text(
            elemento.descripcionLarga,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onBack
        ) {
            Text("Volver")
        }
    }
}