package com.example.proyectoviewmodel.ui.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyectoviewmodel.modelo.Pedido
import com.example.proyectoviewmodel.modelo.PizzeriaUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PizzeriaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PizzeriaUIState())

    val uiState: StateFlow<PizzeriaUIState> = _uiState.asStateFlow()

    private lateinit var tipoPizza: String

    private lateinit var tamanoPizza: String

    private lateinit var tipoBebida: String



    fun PrecioPizza(){
        val precioPizza : Double

        if (tipoPizza == "Pequeña"){

        }

    }
}