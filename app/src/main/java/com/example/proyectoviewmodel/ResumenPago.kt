package com.example.proyectoviewmodel


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoviewmodel.modelo.Pedido
import com.example.proyectoviewmodel.ui.ui.viewmodel.PizzeriaViewModel

@Composable
fun PantallaResumenPago(
    onVolver: () -> Unit,
    onPagar: () -> Unit,
    PizzeriaViewModel: PizzeriaViewModel,
) {
    val uiState by PizzeriaViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.resumenpago),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp)
        )

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
                    Text(" Tipo de tarjeta: ${uiState.tipotarjeta}", fontSize = 18.sp)
                    Text(" Numero de tarjeta: ${uiState.numerotarjeta}", fontSize = 18.sp)
                    Text(" Fecha de caducidad: ${uiState.fechacaducidad}", fontSize = 18.sp)
                    Text("️ CVC: ${uiState.cvc}", fontSize = 18.sp)

                }
                Spacer(modifier = Modifier.weight(1F))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {


                    Button(onClick = onVolver)
                    // REDIRIGIR A RESUMEN DEL PAGO
                    {
                        Text(stringResource(R.string.cancelar))
                    }

                    Button(onClick = onPagar,

                        )  {
                        Text(stringResource(R.string.confirmar))
                    }



                }


            }
        }
    }
