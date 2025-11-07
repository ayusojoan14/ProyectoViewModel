package com.example.proyectoviewmodel
import com.example.proyectoviewmodel.datos.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaInicio(
    onListarPedido: ()-> Unit,
    onRealizarPedido: ()-> Unit,
    modifier: Modifier = Modifier
){
    val imagen = painterResource(id=R.drawable.logo)
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .padding(16.dp), // Margen general
        verticalArrangement = Arrangement.Center, // Centra verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
    ){

        Text(
            stringResource(R.string.bienvenido),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )


        Image(
            painter = imagen,
            contentDescription = null
        )


        Text("${stringResource(R.string.nombre)} $NOMBRE")
        Text("${stringResource(R.string.apellido)} $APELLIDO1 $APELLIDO2")
        Text("${stringResource(R.string.correo)} $CORREO")
        Text("${stringResource(R.string.numero_telefono)} $NUMERO")

        Spacer(modifier = Modifier.weight(1F))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            Button(onClick = onListarPedido)  {
                Text(stringResource(R.string.listar_pedidos))
            }

            Button(onClick = onRealizarPedido)
                // REDIRIGIR A RESUMEN DEL PAGO
             {
                Text(stringResource(R.string.realizar_pedido))
            }

        }


    }
}
