package com.example.tfgym.principal.ui.crearRutinas.ejercicios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun EjerciciosScreen(){
    // Estado para almacenar el texto de búsqueda
    val searchTextState = remember { mutableStateOf("") }

    // Obtener la lista de ejercicios basada en el texto de búsqueda
    val ejercicios = remember { mutableStateListOf<Ejercicio>() }

    // Obtener la lista de ejercicios basada en el texto de búsqueda
    val resultados = remember { mutableStateListOf<Ejercicio>() }



    Column {
        // Barra de búsqueda

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchTextState.value,
                onValueChange = {
                    searchTextState.value = it
                },
                label = { Text("Buscar ejercicio") },
            )

            IconButton(
                onClick = {
                    obtenerEjercicios(searchTextState.value, resultados)
                }
            ) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Buscar",
                    tint = Gray
                )
            }
        }

            if (resultados.isNotEmpty()) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(resultados) { ejercicio ->
                        EjercicioItem(ejercicio)
                    }
                }
            } else {
                // Mostrar la lista original
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(ejercicios) { ejercicio ->
                        EjercicioItem(ejercicio)
                    }
                }
            }
        }
    }

@Preview
@Composable
fun EjerciciosScreenPreview(){
    EjerciciosScreen()
}

@Composable
fun EjercicioItem(ejercicio: Ejercicio) {
    // Mostrar el nombre del ejercicio u otras propiedades según sea necesario
    Text(text = ejercicio.name)
}

fun obtenerEjercicios(searchText: String, ejercicios: SnapshotStateList<Ejercicio>) {

    val db = Firebase.firestore
    val ejerciciosCollection = db.collection("Ejercicios")


    //Búsqueda de ejercicios
    ejerciciosCollection
        .get()
        .addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                val ejercicio = document.toObject(Ejercicio::class.java)
                if (ejercicio?.name == searchText){
                    ejercicio?.let {
                        ejercicios.add(it)
                    }
                }
            }
        }
        .addOnFailureListener { exception ->
            println("Error en la consulta: ${exception.message}")
        }
}