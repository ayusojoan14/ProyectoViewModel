package com.example.proyectoviewmodel.modelo

data class Pedido(
    var tipoPizza: String = "",
    var carne: String = "",
    var tamano: String = "",
    var bebida: String = "",
    var cantidad: Int,
    var cantidadPizzas: Int,
    var precioPizza: Double,
    var precioBebida: Double,
    var precioFinal: Double
)
