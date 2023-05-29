package com.example.tfgym.principal.ui.crearRutinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.tfgym.principal.ui.crearRutinas.ejercicios.obtenerEjercicios

class CreacionRutinas : AppCompatActivity(), crearRutinas {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            RutinasScreen(this)
        }
    }

    override fun crearRutina(): Rutina {
        val rutina = Rutina(mutableListOf())
        return rutina
    }
}