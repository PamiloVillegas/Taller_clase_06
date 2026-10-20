
package com.example.taller_6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ElementosViewModel : ViewModel() {

    private val _elementos = MutableStateFlow<List<Elemento>>(emptyList())
    val elementos: StateFlow<List<Elemento>> = _elementos

    private val _cargando = MutableStateFlow(true)
    val cargando: StateFlow<Boolean> = _cargando

    init {
        cargarElementos()
    }

    fun cargarElementos() {
        viewModelScope.launch {
            _cargando.value = true

            delay(1000)

            _elementos.value = listaPeliculas

            _cargando.value = false
        }
    }

    fun marcarFavorito(id: Int) {
        _elementos.value = _elementos.value.map {
            if (it.id == id) {
                it.copy(esFavorito = !it.esFavorito)
            } else {
                it
            }
        }

    }
}
