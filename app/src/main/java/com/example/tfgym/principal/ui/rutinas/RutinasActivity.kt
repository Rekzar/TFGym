package com.example.tfgym.principal.ui.rutinas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.EjerciciosActivity

class RutinasActivity : AppCompatActivity(), RutinasAction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                RutinasScreen(this)
        }
    }

    override fun crearRutina() {
        val intent = Intent(this, EjerciciosActivity::class.java)
        startActivity(intent)
    }

    override fun mostrarRutina() {
        TODO("Not yet implemented")
    }
}