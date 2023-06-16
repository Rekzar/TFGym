package com.example.tfgym.principal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.tfgym.calendario.CalendarioActivity
import com.example.tfgym.nutricion.NutricionActivity
import com.example.tfgym.principal.ui.rutinas.RutinasActivity

class PrincipalActivity : AppCompatActivity(), MainNav {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MainScreen(this)
        }
    }

    override fun navRutinas() {
        val intent = Intent(this, RutinasActivity::class.java)
        startActivity(intent)
    }

    override fun navNutricion() {
        val intent = Intent(this, NutricionActivity::class.java)
        startActivity(intent)
    }

    override fun navCalendario() {
        val intent = Intent(this, CalendarioActivity::class.java)
        startActivity(intent)
    }
}