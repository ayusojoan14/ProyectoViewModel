package com.example.proyectoviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectoviewmodel.modelo.Bebida
import com.example.proyectoviewmodel.modelo.Pedido
import com.example.proyectoviewmodel.modelo.Pizza
import com.example.proyectoviewmodel.modelo.PizzeriaUIState
import com.example.proyectoviewmodel.ui.ui.theme.ProyectoViewModelTheme
import com.example.proyectoviewmodel.ui.ui.viewmodel.PizzeriaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoViewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SeleccionPediodo()

                }
            }
        }
    }
}
@Composable
fun SeleccionPediodo(
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel = viewModel()
){
    val uiState by PizzeriaViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
    ) {
        Row {
            SeleccionarPizza(modifier = Modifier.padding(top = 25.dp))
        }
        Row {
            SeleccionarTamano(modifier = Modifier.padding())
        }
        Row {
            Precio(
                modifier = Modifier.padding(),
                pizza = uiState.pizza,
                bebida = uiState.bebida,
                tamanopizza = uiState.tamanopizza,
                bebidaS = uiState.preciobebida,
                pPizza = uiState.preciopizza,
                precio = uiState.preciofinal
            )
        }

//        Botones()
    }

    Column (modifier = modifier
        .padding(start = 200.dp)
    ){
        Row {
            SeleccionarBebida(modifier = Modifier.padding(top = 25.dp))
        }

    }
}


@Composable
fun SeleccionarPizza(
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel = viewModel()
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
            .padding( 30.dp)
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
    PizzeriaViewModel: PizzeriaViewModel = viewModel()
) {
    // 🔹 Recogemos el estado actual del ViewModel
    val uiState by PizzeriaViewModel.uiState.collectAsState()

    val opciones = listOf(
        stringResource(R.string.cola),
        stringResource(R.string.agua),
        stringResource(R.string.sinbebida),
    )

    Column(
        modifier = modifier
            .selectableGroup()
            .padding( all = 30.dp )
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
    PizzeriaViewModel: PizzeriaViewModel = viewModel()
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
            .padding( all = 30.dp )
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
    PizzeriaViewModel: PizzeriaViewModel = viewModel()
){
    PizzeriaViewModel.precioenTotal(bebidaS,pPizza)
    Column {
        Text("El precio del pedido  ${pizza} de tamaño ${tamanopizza} y ${bebida} es de ${precio.toString()}")
    }
}








//fun Botones(
//    modifier: Modifier = Modifier,
//    PizzeriaViewModel: PizzeriaViewModel = viewModel()
//){
//    val uiState by PizzeriaViewModel.uiState.collectAsState()
//
//    Text("Cantidad de bebidas")
//
//    Row(
//        modifier = Modifier.padding(vertical = 8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Button(onClick = {
//            if (cantidadBebidas > 0) cantidadBebidas--
//        }) {
//            Text("-")
//        }
//        Text(
//            text = cantidadBebidas.toString(),
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
//        Button(onClick = {
//            cantidadBebidas++
//        }) {
//            Text("+")
//        }
//    }
//}
//
