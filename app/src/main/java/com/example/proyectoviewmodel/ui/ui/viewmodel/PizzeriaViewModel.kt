package com.example.proyectoviewmodel.ui.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyectoviewmodel.modelo.Bebida
import com.example.proyectoviewmodel.modelo.Pago
import com.example.proyectoviewmodel.modelo.Pedido
import com.example.proyectoviewmodel.modelo.Pizza
import com.example.proyectoviewmodel.modelo.PizzeriaUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PizzeriaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PizzeriaUIState())

    val uiState: StateFlow<PizzeriaUIState> = _uiState.asStateFlow()

    private lateinit var tipoPizza: String


    private lateinit var bebida: Bebida
    private lateinit var pago: Pago
    private  var cantidadbebidas: Int =0
    private  var cantidadpizzas: Int =0


    private lateinit var tamanoPizza: String

    private lateinit var tipoBebida: String


    fun seleccionarPizza(pizza: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(pizza = pizza)
        }
    }

    fun seleccionarBebida(bebida: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(bebida = bebida)
        }
    }


}