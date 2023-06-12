package com.example.tfgym.principal.ui.rutinas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.EjerciciosActivity
import com.example.tfgym.principal.ui.rutinas.mostrarRutina.verRutinaActivity


class RutinasActivity : AppCompatActivity(), RutinasAction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                RutinasScreen(this)
        }
    }

    override fun onStart() {
        super.onStart()
        setContent {
            RutinasScreen(this)
        }
    }

    override fun crearRutina() {
        val intent = Intent(this, EjerciciosActivity::class.java)
        startActivity(intent)
    }

    override fun mostrarRutina(rutina: Rutina) {
        val intent = Intent(this, verRutinaActivity::class.java)
        intent.putExtra("rutina", rutina)
        startActivity(intent)
    }

}