package com.example.tfgym.principal.ui.crearRutinas.ejercicios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.google.firebase.firestore.FirebaseFirestore

class Ejercicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjerciciosScreen()
        }
    }

}

