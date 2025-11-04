package com.example.proyectoviewmodel.modelo

data class Pedido(
    val pizza: Pizza,
    val bebida: String,
    val cantidadbebidas: Int ,
    val cantidadpizzas: Int,
    val preciopizza: Double
)
