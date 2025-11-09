package com.example.proyectoviewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectoviewmodel.ui.ui.viewmodel.PizzeriaViewModel


@Composable
fun SeleccionPedido(
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel,
    onAceptar: ()-> Unit,
    onCancelar: ()-> Unit
){
    val uiState by PizzeriaViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
    ) {
        Row {
            SeleccionarPizza(modifier = Modifier.padding(top = 25.dp),
                PizzeriaViewModel= PizzeriaViewModel )
        }
        Row {
            SeleccionarTamano(modifier = Modifier.padding(),
                PizzeriaViewModel = PizzeriaViewModel)
        }
        Row {
            Precio(
                modifier = Modifier.padding(),
                pizza = uiState.pizza,
                bebida = uiState.bebida,
                tamanopizza = uiState.tamanopizza,
                bebidaS = uiState.preciobebida,
                pPizza = uiState.preciopizza,
                precio = uiState.preciofinal,
                PizzeriaViewModel = PizzeriaViewModel
            )
        }
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
                BotonesB( PizzeriaViewModel = PizzeriaViewModel)
            }
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                BotonesP(
                    PizzeriaViewModel = PizzeriaViewModel
                )
            }
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                PrecioFinal(
                    pPizza = uiState.preciopizza,
                    pBebida = uiState.preciobebida,
                    cPizza = uiState.cantidadpizzas,
                    cBebida = uiState.cantidadbebidas,
                    total = uiState.preciofinal,
                    PizzeriaViewModel = PizzeriaViewModel
                )
            }
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

    Column (modifier = modifier
        .padding(start = 200.dp)
    ){
        Row {
            SeleccionarBebida(modifier = Modifier.padding(top = 25.dp),
                PizzeriaViewModel = PizzeriaViewModel)
        }
    }
}


@Composable
fun SeleccionarPizza(
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel
) {
    // 🔹 Recogemos el estado actual del ViewModel
    val uiState by PizzeriaViewModel.uiState.collectAsState()

    val opciones = listOf(
        stringResource(R.string.Barbacoa),
        stringResource(R.string.Romana),
        stringResource(R.string.Margarita),
    )

    Column(
        modifier = modifier
            .selectableGroup()
            .padding(30.dp)
    ) {
        Text("Seleccione la pizza")

        Column(modifier.selectableGroup()) {
            opciones.forEach { text ->
                Row(
                    Modifier
                        .height(50.dp)
                        .selectable(
                            selected = (text == uiState.pizza), // 🔹 valor del estado
                            onClick = { PizzeriaViewModel.seleccionarPizza(text) }, // 🔹 actualiza ViewModel
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == uiState.pizza),
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
fun SeleccionarBebida(
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel
) {
    //Recogemos el estado actual del ViewModel
    val uiState by PizzeriaViewModel.uiState.collectAsState()
    val opciones = listOf(
        stringResource(R.string.cola),
        stringResource(R.string.agua),
        stringResource(R.string.sinbebida),
    )

    Column(
        modifier = modifier
            .selectableGroup()
            .padding(all = 30.dp)
    ) {
        Text("Seleccione la bebida")

        Column(modifier.selectableGroup()) {
            opciones.forEach { text ->
                Row(
                    Modifier
                        .height(50.dp)
                        .selectable(
                            selected = (text == uiState.bebida), // 🔹 valor del estado
                            onClick = { PizzeriaViewModel.seleccionarBebida(text) }, // 🔹 actualiza ViewModel
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == uiState.bebida),
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
fun SeleccionarTamano (
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel
){
    // 🔹 Recogemos el estado actual del ViewModel
    val uiState by PizzeriaViewModel.uiState.collectAsState()

    val opciones = listOf(
        stringResource(R.string.pequena),
        stringResource(R.string.mediana),
        stringResource(R.string.grande),
    )

    Column(
        modifier = modifier
            .selectableGroup()
            .padding(all = 30.dp)
    ) {
        Text("Seleccione el tamaño:")

        Column(modifier.selectableGroup()) {
            opciones.forEach { text ->
                Row(
                    Modifier
                        .height(50.dp)
                        .selectable(
                            selected = (text == uiState.tamanopizza), // valor del estado
                            onClick = { PizzeriaViewModel.seleccionarTamano(text) }, //actualiza ViewModel
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == uiState.tamanopizza),
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
fun Precio(
    modifier: Modifier = Modifier,
    pizza: String,
    bebida: String,
    tamanopizza: String,
    bebidaS: Double,
    pPizza: Double,
    precio: Double,
    PizzeriaViewModel: PizzeriaViewModel
){
    PizzeriaViewModel.precioenTotal(bebidaS,pPizza)
}

@Composable
fun BotonesB(
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel,
) {

    var cantidaN by remember { mutableStateOf(0) }
    Column {
        Text(stringResource(R.string.cantidad_de_bebidas))
        Row(

        ) {
            Button(onClick = {
                if (cantidaN > 0)
                    cantidaN--
            }) {
                Text("-")
            }

            Text(
                text = cantidaN.toString(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            PizzeriaViewModel.bebidasS(cantidaN)

            Button(onClick = {
                cantidaN++
            }) {
                Text("+")
            }
        }
    }
}

@Composable
fun BotonesP(
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel ,
) {

    var cantidaN by remember { mutableStateOf(1) }
    Column (
    ){
        Text(stringResource(R.string.cantidad_de_pizzas))
        Row(
        ) {
            Button(onClick = {
                if (cantidaN > 1) cantidaN--
            }) {
                Text("-")
            }

            Text(
                text = cantidaN.toString(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            PizzeriaViewModel.pizzaCN(cantidaN)

            Button(onClick = {
                cantidaN++
            }) {
                Text("+")
            }
        }
    }
}

@Composable
fun PrecioFinal(
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel ,
    pPizza: Double,
    pBebida: Double,
    cPizza: Int,
    cBebida: Int,
    total: Double
){
    PizzeriaViewModel.precioconTodo(pPizza,pBebida,cPizza,cBebida)
    Column (
    ){
        Row (
        ){
            Text(
                text = stringResource(R.string.precio_total) +total.toString()+" €" ,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
    }

}

@Composable
fun crearPizza( tip: String,
                tamp: String,
                modifier: Modifier = Modifier,
                PizzeriaViewModel: PizzeriaViewModel = viewModel(),
){
    PizzeriaViewModel.crearPizza(tip,tamp)
}
@Composable
fun crearBebida(
    tip: String,
    p: Double,
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel = viewModel(),
){
    PizzeriaViewModel.crearBbeida(tip,p)
}