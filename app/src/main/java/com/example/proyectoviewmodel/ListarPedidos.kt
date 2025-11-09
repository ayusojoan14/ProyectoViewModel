package com.example.proyectoviewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectoviewmodel.modelo.Pedido
import com.example.proyectoviewmodel.ui.ui.viewmodel.PizzeriaViewModel
import java.util.Objects.toString
import kotlin.collections.forEach


@Composable
fun Pedidos(
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel,
    onVolver: ()-> Unit
) {
    val uiState by PizzeriaViewModel.uiState.collectAsState()
    Column(

    ){
        ListarPedido(
            listarPedido = uiState.listaPedidos
        )
        Spacer(modifier = Modifier.weight(1F))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = onVolver)
            // REDIRIGIR A RESUMEN DEL PAGO
            {
                Text(stringResource(R.string.volver))
            }
        }
    }




}
@Composable
fun ListarPedido(
    modifier: Modifier = Modifier,
    listarPedido: MutableSet<Pedido> = mutableSetOf()
){
    Column(modifier = modifier) {
        listarPedido.forEach { pedido ->
            Card(
                modifier = Modifier
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Pizza: ${pedido.pizza}")
                    Text("Tamaño: ${pedido.tamanyo}")
                    Text("Bebida: ${pedido.bebida}")
                    Text("Cantidad de pizzas: ${pedido.cantidadPizzas}")
                    Text("Cantidad de bebidas: ${pedido.cantidadBebidas}")
                    Text("Total: ${pedido.total} €")
                }
            }
        }
    }
}
