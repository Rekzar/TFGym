package com.example.tfgym.principal.ui.crearRutinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent

class CreacionRutinas : AppCompatActivity(), crearRutinas {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            RutinasScreen(this)
        }
    }

    override fun crearRutina() {

    }
}