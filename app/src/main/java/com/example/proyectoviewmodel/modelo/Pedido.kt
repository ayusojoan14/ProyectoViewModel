package com.example.proyectoviewmodel.modelo

data class Pedido(
    val pizza: String,
    val tamanyo: String,
    val bebida: String,
    val cantidadPizzas: Int,
    val cantidadBebidas: Int,
    val total: Double,

)
