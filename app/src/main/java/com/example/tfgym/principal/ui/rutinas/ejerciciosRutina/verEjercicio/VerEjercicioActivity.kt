package com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.verEjercicio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio

class VerEjercicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ejercicio = intent.getSerializableExtra("ejercicio") as? Ejercicio
        setContent {
            if (ejercicio != null) {
                VerEjercicioScreen(ejercicio)
            }
        }
    }
}