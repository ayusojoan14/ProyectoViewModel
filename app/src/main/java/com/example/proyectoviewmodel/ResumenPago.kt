package com.example.proyectoviewmodel


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResumenPago( modifier: Modifier = Modifier){

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){Row(
        modifier = modifier
    ) { Text("Resumen del pago ",
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp )


    }

        Text("Se ha realizado el pago correctamente")
        Text("Con un importe total de: 6.50€")
        BotonesAceptar()
    }
}
@Composable
fun BotonesAceptar(modifier: Modifier = Modifier){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){


        Button(onClick = {

        }) {
            Text("Aceptar")
        }

        Button(onClick = {

        }) {
            Text("Enviar")
        }

    }
}