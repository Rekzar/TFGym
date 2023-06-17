package com.example.tfgym.principal.ui.rutinas.mostrarRutina

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio

@Composable
fun VerRutinaScreen(rutina: Rutina, verRutinaAction: verRutinaAction?, remitente: Boolean) {

    //Convertir la mutableList de rutina en una SnapshotStateList para poder actualizarla
    val listaEjercicios = remember { mutableStateListOf(*rutina.listaEjercicios.toTypedArray()) }

    Scaffold(
        floatingActionButton = {
            // Botón flotante para añadir ejercicios
            FloatingActionButton(
                onClick = {
                    verRutinaAction?.añadirEjercicio(rutina)
                },
                content = { Icon(Icons.Filled.Add, contentDescription = "Añadir ejercicios") }
            )
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "TFGym")},
                navigationIcon = {
                    IconButton(onClick = {
                        if(remitente == true) {
                            verRutinaAction?.volverRutina()
                        } else {
                            verRutinaAction?.volverCalendario()
                        }
                    }){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = rutina.nombreRutina,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(listaEjercicios) { ejercicio ->
                    EjercicioItem(ejercicio = ejercicio, verRutinaAction, rutina, listaEjercicios)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


@Composable
fun EjercicioItem(ejercicio: Ejercicio, verRutinaAction: verRutinaAction?, rutina: Rutina, listaEjercicios: SnapshotStateList<Ejercicio>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = ejercicio.name, modifier = Modifier.weight(2f/3))
        Spacer(modifier = Modifier.width(16.dp))
        Box(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = rememberImagePainter(ejercicio.url),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column{
                    Button(
                        onClick = { verRutinaAction?.mostrarEjercicio(ejercicio) }
                    ) {
                        Text(text = "Ver ejercicio")
                    }
                    Button(
                        onClick = {
                            rutina.eliminarEjercicio(ejercicio)
                            listaEjercicios.remove(ejercicio)
                        }
                    ) {
                        Text(text = "Eliminar ejercicio")
                    }
                }
            }
        }
    }
}
