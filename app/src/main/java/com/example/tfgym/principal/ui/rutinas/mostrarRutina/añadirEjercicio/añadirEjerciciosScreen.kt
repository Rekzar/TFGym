package com.example.tfgym.principal.ui.rutinas.mostrarRutina.añadirEjercicio

import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun añadirEjerciciosScreen(rutina: Rutina, añadirEjerciciosAction: añadirEjerciciosAction?){
    // Estado para almacenar el texto de búsqueda
    val searchTextState = remember { mutableStateOf("") }

    // Obtener la lista de ejercicios basada en el texto de búsqueda
    val listaEjercicios = remember { mutableStateListOf<Ejercicio>() }

    // Obtener la lista de ejercicios basada en el texto de búsqueda
    val resultados = remember { mutableStateListOf<Ejercicio>() }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(// Barra de búsqueda
                value = searchTextState.value,
                onValueChange = {
                    searchTextState.value = it
                    obtenerEjercicios(searchTextState.value, resultados)
                },
                label = { Text("Buscar ejercicio") },
                modifier = Modifier.weight(1f)
            )

            IconButton(// Botón de borrar
                onClick = {
                    searchTextState.value = ""
                    resultados.clear()
                }) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Borrar texto"
                )
            }
        }
        // Lista de resultados de búsqueda
        if (resultados.isNotEmpty() && searchTextState.value != "") {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(resultados) { ejercicio ->
                    ResultadoItem(searchTextState, ejercicio, listaEjercicios, resultados, añadirEjerciciosAction)
                }
            }
        } else {
            // Mostrar la lista de ejercicios añadidos
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(listaEjercicios) { ejercicio ->
                    EjercicioItem(ejercicio, listaEjercicios, añadirEjerciciosAction)
                }
            }
        }

        OutlinedButton(onClick = { rutina.añadirEjercicios(listaEjercicios) }) {
            Text(modifier = Modifier.fillMaxWidth(),
                text = "Añadir ejercicios",
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun ResultadoItem(
    searchTextState: MutableState<String>,
    ejercicio: Ejercicio,
    listaEjercicios: SnapshotStateList<Ejercicio>,
    resultados: SnapshotStateList<Ejercicio>,
    añadirEjerciciosAction: añadirEjerciciosAction?
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ){
        Row(){
            Text(modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
                text = ejercicio.name)
            Column{
                OutlinedButton(onClick = {
                    añadirEjerciciosAction?.mostrarEjercicio(ejercicio)
                }) {
                    Text(text = "Ver ejercicio")
                }
                OutlinedButton(onClick = {
                    listaEjercicios.add(ejercicio)
                    resultados.clear()
                    searchTextState.value = ""
                }) {
                    Text(text = "Añadir ejercicio")
                }
            }
        }
    }
}

@Composable
fun EjercicioItem(ejercicio: Ejercicio, listaEjercicios: SnapshotStateList<Ejercicio>, añadirEjerciciosAction: añadirEjerciciosAction?){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ){
        Row(){
            Text(modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
                text = ejercicio.name)

            Column {
                OutlinedButton(onClick = {
                    añadirEjerciciosAction?.mostrarEjercicio(ejercicio)
                }) {
                    Text(text = "Ver ejercicio")
                }
                OutlinedButton(onClick = {
                    listaEjercicios.remove(ejercicio)
                }) {
                    Text(text = "Eliminar ejercicio")
                }
            }
        }
    }
}

fun obtenerEjercicios(searchText: String, resultados: SnapshotStateList<Ejercicio>) {

    val db = Firebase.firestore
    val ejerciciosCollection = db.collection("Ejercicios")

    //Búsqueda de ejercicios
    ejerciciosCollection
        .whereGreaterThanOrEqualTo("name", searchText.capitalize())
        .whereLessThanOrEqualTo("name", searchText.capitalize() + "\uf8ff")
        .get()
        .addOnSuccessListener { querySnapshot ->
            resultados.clear()
            for (document in querySnapshot.documents) {
                val ejercicio = document.toObject(Ejercicio::class.java)
                ejercicio?.let {
                    resultados.add(it)
                }
            }
        }
        .addOnFailureListener { exception ->
            println("Error en la consulta: ${exception.message}")
        }
}

