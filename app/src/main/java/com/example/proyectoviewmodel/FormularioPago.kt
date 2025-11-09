package com.example.proyectoviewmodel

import android.media.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectoviewmodel.ui.ui.viewmodel.PizzeriaViewModel
import kotlin.String

@Composable
fun FormularioPago(
    modifier: Modifier = Modifier,
    onAceptar: ()-> Unit,
    onCancelar: ()-> Unit,
    PizzeriaViewModel: PizzeriaViewModel
){
    val uiState by PizzeriaViewModel.uiState.collectAsState()
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        TipoTarjeta(
            PizzeriaViewModel = PizzeriaViewModel
        )
        NumeroTarjeta(
            numTarjeta = uiState.numerotarjeta,
            PizzeriaViewModel = PizzeriaViewModel
        )

        FechaCaducidad(
            fecha = uiState.fechacaducidad,
            PizzeriaViewModel = PizzeriaViewModel
        )
        CVC(
            cvc = uiState.cvc,
            PizzeriaViewModel = PizzeriaViewModel
        )
        Spacer(modifier = Modifier.weight(1F))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {


            Button(onClick = onCancelar)
            // REDIRIGIR A RESUMEN DEL PAGO
            {
                Text(stringResource(R.string.cancelar))
            }

            Button(onClick = onAceptar)  {
                Text(stringResource(R.string.aceptar))
            }



        }

    }

}

@Composable
fun TipoTarjeta (
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel
){
    val uiState by PizzeriaViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .selectableGroup()
            .padding(top = 60.dp)
    ) {
        Text(stringResource(R.string.seleccione_las_opciones_correctas))
        val opciones = listOf(
                stringResource(R.string.VISA),
                stringResource(R.string.MasterCard),
                stringResource(R.string.euros_6000)
        )
        val opcionesImagenes = listOf(
            painterResource(id=R.drawable.visa),
            painterResource(id=R.drawable.mastercard),
            painterResource(id=R.drawable.euros6000)
        )
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(opciones[0]) }
        // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
        Column(modifier.selectableGroup()) {
            opciones.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .selectable(
                            selected = (text == uiState.tipotarjeta),
                            onClick = { PizzeriaViewModel.seleccionarTarjeta(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    RadioButton(
                        selected = (text == uiState.tipotarjeta),
                        onClick = null
                    )


                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

    }
}

@Composable
fun NumeroTarjeta (
    numTarjeta: String,
    PizzeriaViewModel: PizzeriaViewModel
){
    Column (
        modifier = Modifier,

        ){

    }
    Row{
        OutlinedTextField(
            value = numTarjeta,
            onValueChange ={ PizzeriaViewModel.actualizarNumeroTarjeta(it) },
            label = { Text(stringResource(R.string.numero_de_tarjeta)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)

        )
    }
}

@Composable
fun FechaCaducidad (
    modifier: Modifier = Modifier,
    fecha: String,
    PizzeriaViewModel: PizzeriaViewModel
    ){
    Column (
        modifier = Modifier,
    ){

    }
    Row{
        OutlinedTextField(
            value = fecha,
            onValueChange = { PizzeriaViewModel.actualizarfecha(it)   },
            label = { Text(stringResource(R.string.fecha)) },
           singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
    }
}

@Composable
fun CVC (
    modifier: Modifier = Modifier,
    cvc: String,
    PizzeriaViewModel: PizzeriaViewModel

){
    Column (
        modifier = Modifier,
    ){

    }
    Row{
        OutlinedTextField(
            value = cvc,
            onValueChange = { PizzeriaViewModel.actualizarcvc(it)   },
            label = { Text(stringResource(R.string.cvc)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
    }
}
