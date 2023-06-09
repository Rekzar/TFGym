package com.example.tfgym.principal.ui.rutinas

import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Rutina (
    val listaEjercicios: MutableList<Ejercicio>,
    val nombreRutina: String,
    val idUsuario: String?,
    var documentoReferencia: DocumentReference?) : java.io.Serializable{

    constructor(): this(mutableListOf(),"",null, null)


    fun eliminarDocumento() {
        documentoReferencia?.delete()
            ?.addOnSuccessListener {
                println("Documento eliminado correctamente")
            }
            ?.addOnFailureListener { exception ->
                println("Error al eliminar el documento: ${exception.message}")
            }
    }
}