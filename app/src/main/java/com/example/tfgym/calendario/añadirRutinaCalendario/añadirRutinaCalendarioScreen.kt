package com.example.tfgym.calendario.añadirRutinaCalendario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.obtenerRutinas

@Composable
fun AñadirRutinaCalendarioScreen(añadirRutinaCalendarioAction: añadirRutinaCalendarioAction?, currentDay: String){

    val listaRutinas = remember { mutableStateListOf<Rutina>() }

    obtenerRutinas(listaRutinas)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "TFGym")},
                navigationIcon = {
                    IconButton(onClick = { añadirRutinaCalendarioAction?.volverCalendario() }){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Espacio vacío en la parte superior
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Lista de rutinas
            items(listaRutinas) { rutina ->
                RutinaItem(rutina, añadirRutinaCalendarioAction, currentDay)
            }
        }
    }
}

@Composable
fun RutinaItem(rutina: Rutina, añadirRutinaCalendarioAction: añadirRutinaCalendarioAction?, currentDay: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
    ) {
        Text(
            text = rutina.nombreRutina,
            modifier = Modifier
                .padding(16.dp)
        )
        OutlinedButton(onClick = {
            añadirRutinaCalendarioAction?.añadirRutina(rutina, currentDay)
        },
        modifier = Modifier.align(Alignment.CenterEnd).padding(end = 8.dp)) {
            Text("Añadir rutina")
        }
    }
}