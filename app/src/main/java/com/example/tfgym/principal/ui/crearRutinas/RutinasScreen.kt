package com.example.tfgym.principal.ui.crearRutinas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp


@Composable
fun RutinasScreen( creacionRutinas: crearRutinas) {
    // Lista de rutinas
    val rutinasList = remember { mutableStateListOf<String>() }

    // Variable para almacenar el nombre de la nueva rutina
    var nuevaRutina by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            // Botón flotante para crear una nueva rutina
            FloatingActionButton(
                onClick = {
                    creacionRutinas.crearRutina()
                },
                content = { Icon(Icons.Filled.Add, contentDescription = "Crear nueva rutina") }
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
            items(rutinasList) { rutina ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(8.dp)
                        .background(LightGray)
                ) {
                    Text(
                        text = "rutina",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            // Espacio vacío al final de la lista
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
