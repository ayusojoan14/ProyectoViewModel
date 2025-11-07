package com.example.proyectoviewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectoviewmodel.modelo.Bebida
import com.example.proyectoviewmodel.modelo.Pedido
import com.example.proyectoviewmodel.ui.ui.viewmodel.PizzeriaViewModel
import java.util.Objects.toString



@Composable
fun PantallaResumenPedido(
    pedidoActual: Pedido?,
    onVolver: () -> Unit,
    onPagar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "RESUMEN DEL PEDIDO",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        if (pedidoActual == null) {
            Text(
                text = "No hay pedidos realizados",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(" Pizza: ${pedidoActual.pizza}", fontSize = 18.sp)
                    Text(" Tamaño: ${pedidoActual.tamanyo}", fontSize = 18.sp)
                    Text(" Bebida: ${pedidoActual.bebida}", fontSize = 18.sp)
                    Text("️ Cantidad de pizzas: ${pedidoActual.cantidadPizzas}", fontSize = 18.sp)
                    Text(" Cantidad de bebidas: ${pedidoActual.cantidadBebidas}", fontSize = 18.sp)
                    Text(
                        "Total: ${"%.2f".format(pedidoActual.total)}€",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {


                    Button(onClick = onVolver)
                    // REDIRIGIR A RESUMEN DEL PAGO
                    {
                        Text(stringResource(R.string.volver))
                    }

                    Button(onClick = onPagar,

                        )  {
                        Text(stringResource(R.string.pagar))
                    }



                }


            }
        }
    }
}


