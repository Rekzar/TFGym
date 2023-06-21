package com.example.tfgym.principal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.tfgym.calendario.CalendarioActivity
import com.example.tfgym.login.LoginActivity
import com.example.tfgym.nutricion.NutricionActivity
import com.example.tfgym.principal.ui.rutinas.RutinasActivity
import com.google.firebase.auth.FirebaseAuth

class PrincipalActivity : AppCompatActivity(), MainNav {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            PrincipalScreen(this)
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

    override fun cerrarSesion() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

    }
}