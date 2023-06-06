package com.example.tfgym.principal.ui.crearRutinas.ejercicios

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var resultados = remember { mutableStateListOf<Ejercicio>() }

    var original  = true


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
                    resultados.clear()
                    obtenerEjercicios(searchTextState.value, resultados)

                },
                label = { Text("Buscar ejercicio") },
            )

            IconButton(
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

            if (resultados.isNotEmpty() && searchTextState.value != "") {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(resultados) { ejercicio ->
                        ResultadoItem(ejercicio, ejercicios)
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

@Preview(showBackground = true)
@Composable
fun EjerciciosScreenPreview(){
    EjerciciosScreen()
}

@Composable
fun ResultadoItem(ejercicio: Ejercicio, ejercicios: SnapshotStateList<Ejercicio>) {
    // Mostrar el nombre del ejercicio u otras propiedades según sea necesario
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

            OutlinedButton(onClick = {
                ejercicios.add(ejercicio)

            }) {
                Text("Añadir ejercicio")
            }
        }

    }
}

@Composable
fun EjercicioItem(ejercicio: Ejercicio){

    Text(text = ejercicio.name)
}

fun obtenerEjercicios(searchText: String, ejercicios: SnapshotStateList<Ejercicio>) {

    val db = Firebase.firestore
    val ejerciciosCollection = db.collection("Ejercicios")


    //Búsqueda de ejercicios
    ejerciciosCollection
        .whereGreaterThanOrEqualTo("name", searchText.capitalize())
        .whereLessThanOrEqualTo("name", searchText.capitalize() + "\uf8ff")
        .get()
        .addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                Log.d("---", document.toString())
                val ejercicio = document.toObject(Ejercicio::class.java)
//                if (ejercicio?.name == searchText){
                ejercicio?.let {
                    ejercicios.add(it)
                }
//                }
            }
        }
        .addOnFailureListener { exception ->
            println("Error en la consulta: ${exception.message}")
        }
        .addOnCompleteListener {
            println("Consulta completada:")
        }
        .addOnCanceledListener {
            println("Consulta cancelada:")
        }
}
