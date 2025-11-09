package com.example.proyectoviewmodel.ui.ui.viewmodel

import android.R.attr.onClick
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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



    private lateinit var pizza: Pizza
    private lateinit var bebida: Bebida
    private lateinit var pedido: Pedido
    private lateinit var pago: Pago
    private var cantidadbebidas: Int = 0
    private var cantidadpizzas: Int = 0

    private var preciototal: Double = 0.0
    var numerotarjeta by mutableStateOf("")
    val listaPedidos: MutableSet<Pedido> = mutableSetOf()



    // FUNCIONES DE PEDIDO FINAL
    fun realizarPedidoActual() {
        val estado = _uiState.value
        val pedido = Pedido(
            pizza = estado.pizza,
            tamanyo = estado.tamanopizza,
            bebida = estado.bebida,
            cantidadPizzas = estado.cantidadpizzas,
            cantidadBebidas = estado.cantidadbebidas,
            total = estado.preciofinal,
        )
        if (pedido.pizza == "" || pedido.tamanyo == ""){
        }else{
            listaPedidos.add(pedido)
        }

        _uiState.update { it.copy(pedidoActual = pedido, listaPedidos = listaPedidos) }
    }




    fun crearPizza(
        tip: String,
        tamp: String
    ){
        pizza = Pizza(
            tip,
            tamp
        )
        _uiState.update{estadoActual ->
        estadoActual.copy(
            pizza = tip,
            tamanopizza = tamp
        )}
    }
    fun crearBbeida(
        tip: String,
        p: Double
    ){
        bebida = Bebida(
            tip,
            p
        )
        _uiState.update{estadoActual ->
            estadoActual.copy(
                bebida = tip,
                preciobebida = p
            )}
    }
    fun crearPago(tipotarjeta: String,
        numTarjeta: String,
        fecha: String,
        cvc: String ){
        pago = Pago(
            tipotarjeta,
            numTarjeta,
            fecha,
            cvc)
        _uiState.update { estadoActual ->
            estadoActual.copy(
                tipotarjeta = tipotarjeta,
                numerotarjeta = numTarjeta,
                fechacaducidad = fecha,
                cvc = cvc
            )
        }
    }

    fun seleccionarPizza(pizza: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(pizza = pizza)
        }
    }

    fun actualizarNumeroTarjeta(p: String) {
        if (p.length <= 16 && p.all { it.isDigit() })
            _uiState.update { estadoActual ->
                estadoActual.copy(numerotarjeta = p)
            }
    }

    fun seleccionarTarjeta(tarjeta: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(tipotarjeta = tarjeta)
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

    fun actualizarfecha(p: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(fechacaducidad = p)
        }
    }

    fun actualizarcvc(c: String) {
        if (c.length <= 3 && c.all { it.isDigit() })
            _uiState.update { estadoActual ->
                estadoActual.copy(cvc = c)
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
                estadoActual.copy(preciobebida = precioBebida, bebida = bebidaS)
            }
        }

        fun precioenTotal(pBebida: Double, pPizza: Double) {
            val precioTotal: Double = pBebida + pPizza
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    preciobebida = pBebida,
                    preciopizza = pPizza,
                    preciofinal = precioTotal
                )
            }
        }

        fun bebidasS(c: Int) {
            _uiState.update { estadoActual ->
                estadoActual.copy(cantidadbebidas = c)
            }
        }

        fun pizzaCN(c: Int) {
            _uiState.update { estadoActual ->
                estadoActual.copy(cantidadpizzas = c)
            }
        }

        fun precioconTodo(pPizza: Double, pBebida: Double, cPizza: Int, cBebida: Int) {
            preciototal = (pPizza * cPizza) + (pBebida * cBebida)
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    preciopizza = pPizza, preciobebida = pBebida, preciofinal = preciototal,
                    cantidadpizzas = cPizza, cantidadbebidas = cBebida
                )
            }
        }
}


