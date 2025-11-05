package com.example.proyectoviewmodel.ui.ui.viewmodel

import android.R.attr.onClick
import androidx.compose.material3.Text
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
import java.util.function.DoubleUnaryOperator

class PizzeriaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PizzeriaUIState())

    val uiState: StateFlow<PizzeriaUIState> = _uiState.asStateFlow()

    private lateinit var tipoPizza: String

    private lateinit var pizza: Pizza
    private lateinit var bebida: Bebida
    private lateinit var pago: Pago
    private var cantidadbebidas: Int = 0
    private var cantidadpizzas: Int = 0
    private var preciopizza: Double = 0.0
    private var preciobebida: Double = 0.0
    private var preciototal: Double = 0.0

    val listaPedidos: MutableSet<Pedido> = mutableSetOf()


    private lateinit var tipoBebida: String


    fun seleccionarPizza(pizza: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(pizza = pizza)
        }
    }

    fun seleccionarTamano(tamano: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(tamanopizza = tamano)
        }
        val precioP: Double = when (tamano) {
            "Pequeña" -> 4.95
            "Mediana" -> 6.95
            "Grande" -> 10.95
            else -> 0.0

        }
        _uiState.update { estadoActual ->
            estadoActual.copy(preciopizza = precioP, tamanopizza = tamano)
        }

    }

    fun seleccionarBebida(bebidaS: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(bebida = bebidaS)
        }
        val precioBebida: Double = when (bebidaS) {
            "Agua" -> 2.0
            "Cola" -> 2.5
            "Sin bebida" -> 0.0
            else -> 0.0
        }
        _uiState.update { estadoActual ->
            estadoActual.copy(preciobebida = precioBebida, bebida = bebidaS )
        }
    }

    fun precioenTotal(pBebida: Double, pPizza: Double){
        val precioTotal : Double = pBebida+pPizza
        _uiState.update { estadoActual ->
            estadoActual.copy(preciobebida = pBebida, preciopizza = pPizza, preciofinal = precioTotal )
        }
    }
    fun bebidasS(c: Int){
        _uiState.update { estadoActual ->
            estadoActual.copy(cantidadbebidas = c)
        }
    }

    fun pizzaCN(c:Int){
        _uiState.update { estadoActual ->
            estadoActual.copy(cantidadpizzas = c)
        }
    }

    fun precioconTodo(pPizza: Double, pBebida: Double, cPizza: Int, cBebida: Int){
        preciototal  = (pPizza*cPizza)+(pBebida*cBebida)
        _uiState.update { estadoActual ->
            estadoActual.copy(
                preciopizza = pPizza, preciobebida = pBebida, preciofinal = preciototal,
                cantidadpizzas = cPizza, cantidadbebidas = cBebida
            )
        }
    }
}

