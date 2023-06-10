package com.example.tfgym.principal.ui.rutinas


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun RutinasScreen(rutinasAction: RutinasAction?) {
    // Lista de rutinas
    val listaRutinas = remember { mutableStateListOf<Rutina>() }


    obtenerRutinas(listaRutinas)

    Scaffold(
        floatingActionButton = {
            // Botón flotante para crear una nueva rutina
            FloatingActionButton(
                onClick = {
                    rutinasAction?.crearRutina()
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
            items(listaRutinas) { rutina ->
                RutinaItem(rutina, rutinasAction, listaRutinas)
            }

            // Espacio vacío al final de la lista
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun RutinaItem(rutina: Rutina, rutinasAction: RutinasAction?, listaRutinas: SnapshotStateList<Rutina>){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(LightGray)
    ) {
        Text(
            text = rutina.nombreRutina,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(16.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        ){
            OutlinedButton(onClick = {
                rutinasAction?.mostrarRutina(rutina)
            }) {
                Text(text = "Ver rutina")
            }
            OutlinedButton(onClick = {
                rutina.eliminarDocumento()
                listaRutinas.remove(rutina)
            }) {
                Text(text = "Eliminar rutina")
            }
        }

    }
}

@Preview
@Composable
fun RutinasScreenPreview(){
    RutinasScreen(null)
}

fun obtenerRutinas(listaRutinas: SnapshotStateList<Rutina>){
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val db = Firebase.firestore
    val rutinasCollection = db.collection("Rutinas")

    //Búsqueda de ejercicios
    rutinasCollection
        .whereEqualTo("idUsuario", userId)
        .get()
        .addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                val rutina = document.toObject(Rutina::class.java)
                rutina?.let {
                    it.pathDocumento = document.reference.path
                    listaRutinas.add(it)
                }
            }
        }
        .addOnFailureListener { exception ->
            println("Error en la consulta: ${exception.message}")
        }
}