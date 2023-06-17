package com.example.tfgym.principal.ui.rutinas.ejerciciosRutina

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tfgym.nutricion.Alimento
import com.example.tfgym.nutricion.NutricionAction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun NutricionScreen(nutricionAction: NutricionAction?) {
    // Estado para almacenar el texto de búsqueda
    val searchTextState = remember { mutableStateOf("") }

    // Obtener la lista de alimentos basada en el texto de búsqueda
    val listaAlimentos = remember { mutableStateListOf<Alimento>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "TFGym")},
                navigationIcon = {
                    IconButton(onClick = { nutricionAction?.volverPrincipal() }){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
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
                        obtenerAlimentos(searchTextState.value, listaAlimentos)
                    },
                    label = { Text("Buscar alimento") },
                    modifier = Modifier.weight(1f)
                )

                IconButton(// Botón de borrar
                    onClick = {
                        searchTextState.value = ""
                        listaAlimentos.clear()
                    }) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Borrar texto"
                    )
                }
            }
            // Lista de resultados de búsqueda
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(listaAlimentos) { alimento ->
                    AlimentoItem(searchTextState, alimento, nutricionAction)
                }
            }
        }
    }
}

    @Preview(showBackground = true)
    @Composable
    fun NutricionScreenPreview() {
        NutricionScreen(null)
    }

    @Composable
    fun AlimentoItem(
        searchTextState: MutableState<String>,
        alimento: Alimento,
        nutricionAction: NutricionAction?
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .clickable {
                    searchTextState.value = ""
                    nutricionAction?.verAlimento(alimento)
                }
        ) {
            Row() {
                Text(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                    text = alimento.name)
            }
        }
    }

    fun obtenerAlimentos(searchText: String, listaAlimentos: SnapshotStateList<Alimento>) {
        val db = Firebase.firestore
        val alimentosCollection = db.collection("Alimentos")

        //Búsqueda de ejercicios
        alimentosCollection
            .whereGreaterThanOrEqualTo("name", searchText.capitalize())
            .whereLessThanOrEqualTo("name", searchText.capitalize() + "\uf8ff")
            .get()
            .addOnSuccessListener { querySnapshot ->
                listaAlimentos.clear()
                for (document in querySnapshot.documents) {
                    val alimento = document.toObject(Alimento::class.java)
                   alimento?.let{
                       listaAlimentos.add(it)
                   }
                }
            }
            .addOnFailureListener { exception ->
                println("Error en la consulta: ${exception.message}")
            }
    }


