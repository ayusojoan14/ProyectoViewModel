package com.example.proyectoviewmodel.modelo

data class PizzeriaUIState(
    val pizza: String ="",
    val tamanopizza: String ="",
    val bebida: String="",
    val cantidadbebidas: Int =0 ,
    val cantidadpizzas: Int =1,
    val preciopizza: Double=0.0,
    val preciobebida: Double=0.0,
    val preciofinal: Double=0.0,

//    Pago
    val tipotarjeta: String ="",
    val numerotarjeta: String="",
    val fechacaducidad: String = "",
    val cvc: String = "",

    val pedidoActual: Pedido ? =null,
    val listaPedidos: MutableSet<Pedido> = mutableSetOf()
){

}
