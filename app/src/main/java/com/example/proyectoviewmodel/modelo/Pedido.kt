package com.example.proyectoviewmodel.modelo

data class Pedido(
    val pizza: Pizza,
    val bebida: Bebida,
    val cantidadbebidas: Int ,
    val cantidadpizzas: Int,
    val preciofinal: Double
)
