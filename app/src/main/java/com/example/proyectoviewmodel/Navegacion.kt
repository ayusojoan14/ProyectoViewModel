package com.example.proyectoviewmodel

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyectoviewmodel.ui.ui.viewmodel.PizzeriaViewModel
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable




enum class Pantallas(@StringRes val titulo: Int){
    Inicio(titulo = R.string.pantalla_inicio),
    RealizarPedido(titulo = R.string.realizar_pedido),
    ListarPedido(titulo = R.string.listar_pedidos),
    ResumenPedido(titulo = R.string.resumenpedido),
    FormularioPago(titulo = R.string.formulariodepago ),
    ResumenPago(titulo = R.string.resumenpago)

}

@Composable
fun PizzaTime(
    PizzeriaViewModel: PizzeriaViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {


    val pilaRetroceso by navController.currentBackStackEntryAsState()

    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.Inicio.name
    )
    Scaffold(
        topBar = {
            AppTopBar(
                pantallaActual = pantallaActual,
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                onNavegarAtras = { navController.navigateUp() })
        }
    ) { innerPadding ->

        val uiState by PizzeriaViewModel.uiState.collectAsState()

        NavHost(

            navController = navController,
            startDestination = Pantallas.Inicio.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = Pantallas.Inicio.name) {
                PantallaInicio(

                    onListarPedido = {
                        navController.navigate(Pantallas.ListarPedido.name)
                    },
                    onRealizarPedido ={
                        navController.navigate(Pantallas.RealizarPedido.name)
                    }
                    ,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable(route = Pantallas.RealizarPedido.name){
                SeleccionPedido(
                    PizzeriaViewModel = PizzeriaViewModel,
                    onAceptar = {
                        PizzeriaViewModel.realizarPedidoActual()
                        navController.navigate(Pantallas.ResumenPedido.name)
                    },
                    onCancelar = {
                        navController.popBackStack()
                    }
                )
            }
            composable(route = Pantallas.ListarPedido.name){
                Pedidos(
                    PizzeriaViewModel = PizzeriaViewModel,
                    onVolver = {
                        navController.popBackStack()
                    },
                )
            }
            composable (route = Pantallas.ResumenPedido.name ){
                val uiState by PizzeriaViewModel.uiState.collectAsState()
                PantallaResumenPedido(
                    pedidoActual = uiState.pedidoActual,
                    PizzeriaViewModel = PizzeriaViewModel,
                    onVolver = {
                        navController.popBackStack()
                    },
                    onPagar ={ navController.navigate(Pantallas.FormularioPago.name)}
                )
            }
            composable (route = Pantallas.FormularioPago.name ){
                FormularioPago(
                    PizzeriaViewModel = PizzeriaViewModel,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onAceptar = {
                        navController.navigate(Pantallas.ResumenPago.name)
                    }
                )

            }
            composable (route = Pantallas.ResumenPago.name ){
                PantallaResumenPago(
                    PizzeriaViewModel = PizzeriaViewModel,
                    onVolver = {
                        navController.popBackStack()
                    },
                    onPagar = {
                        navController.navigate(Pantallas.Inicio.name)
                        PizzeriaViewModel.realizarPedidoActual()
                    }
                )
            }

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
    puedeNavegarAtras: Boolean,
    onNavegarAtras: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (puedeNavegarAtras) {
                IconButton(onClick = onNavegarAtras) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.atras)
                    )
                }
            }
        },
        modifier = modifier
    )
}
