package com.example.proyectoviewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun FormularioPago(modifier: Modifier = Modifier){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        TipoTarjeta()
        NumeroTarjeta()
        FechaCaducidad()
        CVC()
        Botones()

    }

}
@Composable
fun TipoTarjeta ( modifier: Modifier = Modifier){
    Column(
        modifier = Modifier
            .selectableGroup()
            .padding(top = 60.dp)
    ) {
        Text(stringResource(R.string.seleccione_las_opciones_correctas))
        val opciones = listOf(
            stringResource(R.string.VISA),
            stringResource(R.string.MasterCard),
            stringResource(R.string.euros_6000),
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
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    RadioButton(
                        selected = (text == selectedOption),
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
fun NumeroTarjeta ( modifier: Modifier = Modifier){
    var numTarjeta by remember { mutableStateOf("") }
    Column (
        modifier = Modifier,

        ){

    }
    Row{
        OutlinedTextField(
            value = numTarjeta,
            onValueChange = { numTarjeta = it },
            label = { Text("Número de tarjeta") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )
    }
}

@Composable
fun FechaCaducidad ( modifier: Modifier = Modifier){
    var fechCad by remember { mutableStateOf("") }
    Column (
        modifier = Modifier,
    ){

    }
    Row{
        OutlinedTextField(
            value = fechCad,
            onValueChange = { fechCad = it },
            label = { Text("Fecha de caducidad") },
            singleLine = true,
        )
    }
}

@Composable
fun CVC ( modifier: Modifier = Modifier){
    var cvc by remember { mutableStateOf("") }
    Column (
        modifier = Modifier,
    ){

    }
    Row{
        OutlinedTextField(
            value = cvc,
            onValueChange = { cvc = it },
            label = { Text("CVC") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}
@Composable
fun Botones( modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly,

        ) {


        Button(onClick = {
            // REDIRIGIR A PANTALLA INICIAL
        }) {
            Text("Cancelar")
        }

        Button(onClick = {
            // REDIRIGIR A RESUMEN DEL PAGO
        }) {
            Text("Aceptar")
        }

    }
}