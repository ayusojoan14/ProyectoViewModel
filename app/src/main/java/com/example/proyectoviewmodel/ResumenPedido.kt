package com.example.proyectoviewmodel

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ResumenPedido(modifier: Modifier = Modifier){
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){Row(
        modifier = modifier
    ) { Text("Resumen Del Pedido ",
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp )
    }

        Text("Tipo de pizza: "+stringResource(R.string.Barbacoa)+" con carne de Pollo")
        Text("Tamaño De La Pizza: "+stringResource(R.string.Mediana))
        Text("Bebida:"+stringResource(R.string.Agua))

        Text("El total a pagar son de : 6.50€")

        BotonesCon()

    }
}
@Composable
fun BotonesCon(modifier: Modifier = Modifier){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){


        Button(onClick = {

        }) {
            Text("Cancelar")
        }

        Button(onClick = {

        }) {
            Text("Pagar")
        }

    }
}