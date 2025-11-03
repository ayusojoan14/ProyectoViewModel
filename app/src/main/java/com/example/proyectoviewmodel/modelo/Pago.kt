package com.example.proyectoviewmodel.modelo

data class Pago(
    var tipotarjeta: String ="",
    var numerotarjeta: String="",
    var fechacaducidad: String="",
    var cvc: String=""
)
