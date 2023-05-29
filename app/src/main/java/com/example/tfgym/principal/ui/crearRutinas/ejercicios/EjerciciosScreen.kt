package com.example.tfgym.principal.ui.crearRutinas.ejercicios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun EjerciciosScreen(){
    // Estado para almacenar el texto de búsqueda
    val searchTextState = remember { mutableStateOf("") }

    // Obtener la lista de ejercicios basada en el texto de búsqueda
    val ejercicios = remember { mutableStateListOf<Ejercicio>() }

    // Lógica para obtener los ejercicios según el texto de búsqueda
    obtenerEjercicios(searchTextState.value, ejercicios)

    Column {
        // Barra de búsqueda
        TextField(
            value = searchTextState.value,
            onValueChange = { searchTextState.value = it },
            label = { Text("Buscar ejercicio")},
            modifier = Modifier.fillMaxWidth()
        )
        if (searchTextState.value.isNotEmpty()) {
            DropdownMenu(
                expanded = true,
                onDismissRequest = { searchTextState.value = "" }
            ){
                ejercicios.forEach { ejercicio ->
                    DropdownMenuItem(onClick = { ejercicios.add(ejercicio) }) {
                        Text(ejercicio.name)
                    }
                }
            }
        }

        // Lista de resultados
        LazyColumn {
            items(ejercicios) { ejercicio ->
                EjercicioItem(ejercicio)
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
    val ejercicios = mutableListOf<Ejercicio>()

    val db = FirebaseFirestore.getInstance()
    val ejerciciosCollection = db.collection("ejercicios")

    //Búsqueda dinámica de ejercicios
    ejerciciosCollection.whereGreaterThanOrEqualTo("name", searchText)
        .whereLessThanOrEqualTo("name", searchText + "\uf8ff")
        .get()
        .addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                val ejercicio = document.toObject(Ejercicio::class.java)
                ejercicio?.let {
                    ejercicios.add(it)
                }
            }
        }
        .addOnFailureListener { exception ->
            // Ocurrió un error al obtener los ejercicios desde Firebase Firestore
            exception.printStackTrace()
        }
}