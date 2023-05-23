package com.example.tfgym.principal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.tfgym.calendario.Calendario
import com.example.tfgym.nutricion.Nutricion
import com.example.tfgym.principal.ui.crearRutinas.CreacionRutinas

class PrincipalActivity : AppCompatActivity(), MainNav {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MainScreen(this)
        }
    }

    override fun navRutinas() {
        val intent = Intent(this, CreacionRutinas::class.java)
        startActivity(intent)
    }

    override fun navNutricion() {
        val intent = Intent(this, Nutricion::class.java)
        startActivity(intent)
    }

    override fun navCalendario() {
        val intent = Intent(this, Calendario::class.java)
        startActivity(intent)
    }
}