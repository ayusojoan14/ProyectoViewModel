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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectoviewmodel.modelo.Pedido
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
fun SeleccionPediodo( modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Row {
            SeleccionarPizza(modifier = Modifier.padding())
        }
        Row {
            SeleccionarBebida(modifier = Modifier.padding())
        }

    }
}


@Composable
fun SeleccionarPizza(
    modifier: Modifier = Modifier,
    PizzeriaViewModel: PizzeriaViewModel = viewModel()
) {
    // 🔹 Recogemos el estado actual del ViewModel
    val pizzauiState by PizzeriaViewModel.uiState.collectAsState()

    val opciones = listOf(
        stringResource(R.string.Barbacoa),
        stringResource(R.string.Romana),
        stringResource(R.string.Margarita),
    )

    Column(
        modifier = modifier
            .selectableGroup()
            .padding( 60.dp)
    ) {
        Text("Seleccione la pizza:")

        Column(modifier.selectableGroup()) {
            opciones.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .selectable(
                            selected = (text == pizzauiState.pizza), // 🔹 valor del estado
                            onClick = { PizzeriaViewModel.seleccionarPizza(text) }, // 🔹 actualiza ViewModel
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == pizzauiState.pizza),
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
    val bebidauiState by PizzeriaViewModel.uiState.collectAsState()

    val opciones = listOf(
        stringResource(R.string.cola),
        stringResource(R.string.agua),
        stringResource(R.string.sinbebida),
    )

    Column(
        modifier = modifier
            .selectableGroup()
            .padding( 60.dp)
    ) {
        Text("Seleccione la bebida:")

        Column(modifier.selectableGroup()) {
            opciones.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .selectable(
                            selected = (text == bebidauiState.bebida), // 🔹 valor del estado
                            onClick = { PizzeriaViewModel.seleccionarBebida(text) }, // 🔹 actualiza ViewModel
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == bebidauiState.bebida),
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
