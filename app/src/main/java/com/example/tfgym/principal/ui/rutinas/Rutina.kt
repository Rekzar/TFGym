package com.example.tfgym.principal.ui.rutinas

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Rutina (
    val listaEjercicios: MutableList<Ejercicio>,
    val nombreRutina: String,
    val idUsuario: String?,
    var pathDocumento: String) : java.io.Serializable{

    constructor(): this(mutableListOf(),"",null, "")

    fun eliminarDocumento() {
        FirebaseFirestore.getInstance().document(pathDocumento)
            .delete()
            ?.addOnSuccessListener {
                println("Documento eliminado correctamente")
            }
            ?.addOnFailureListener { exception ->
                println("Error al eliminar el documento: ${exception.message}")
            }
    }

    fun eliminarEjercicio(ejercicio: Ejercicio){
        listaEjercicios.remove(ejercicio)
        FirebaseFirestore.getInstance().document(pathDocumento)
            .update("listaEjercicios", listaEjercicios)
            ?.addOnSuccessListener {
                println("Ejercicio eliminado correctamente")
            }
            ?.addOnFailureListener { exception ->
                println("Error al eliminar el ejercicio: ${exception.message}")
            }
    }

    fun añadirEjercicios(ejercicios: SnapshotStateList<Ejercicio>){

        for(ejercicio in ejercicios){
            if (!listaEjercicios.contains(ejercicio)) {
                listaEjercicios.add(ejercicio)
            }
        }
        FirebaseFirestore.getInstance().document(pathDocumento)
            .update("listaEjercicios", listaEjercicios)
            ?.addOnSuccessListener {
                println("Ejercicio eliminado correctamente")
            }
            ?.addOnFailureListener { exception ->
                println("Error al eliminar el ejercicio: ${exception.message}")
            }
    }
}