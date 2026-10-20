package com.example.taller_6

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ListaScreen(
    onElementoClick: (Int) -> Unit,
    viewModel: ElementosViewModel = viewModel()
) {
    val elementos by viewModel.elementos.collectAsStateWithLifecycle()
    val cargando by viewModel.cargando.collectAsStateWithLifecycle()

    var query by rememberSaveable { mutableStateOf("") }

    val filtrados = remember(query, elementos) {
        if (query.isBlank()) {
            elementos
        } else {
            elementos.filter {
                it.titulo.contains(query, ignoreCase = true)
            }
        }
    }

    if (cargando) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Buscar") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            LazyColumn {
                items(filtrados, key = { it.id }) { elemento ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                onElementoClick(elemento.id)
                            }
                    ) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Text(
                                elemento.icono,
                                style = MaterialTheme.typography.headlineSmall
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    elemento.titulo,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    elemento.descripcionCorta,
                                    style = MaterialTheme.typography.bodySmall
                                )

                                Text(
                                    text = if (elemento.esFavorito) {
                                        "⭐ Favorito"
                                    } else {
                                        "☆ Marcar favorito"
                                    },
                                    modifier = Modifier.clickable {
                                        viewModel.marcarFavorito(elemento.id)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
