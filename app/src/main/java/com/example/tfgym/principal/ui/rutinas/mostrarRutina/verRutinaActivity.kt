package com.example.tfgym.principal.ui.rutinas.mostrarRutina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.tfgym.calendario.CalendarioActivity
import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.RutinasActivity
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.verEjercicio.VerEjercicioActivity
import com.example.tfgym.principal.ui.rutinas.mostrarRutina.añadirEjercicio.añadirEjerciciosActivity

class verRutinaActivity : AppCompatActivity(), verRutinaAction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rutina = intent.getSerializableExtra("rutina") as? Rutina
        val remitente = intent.getBooleanExtra("remitente", false)
        setContent {
            if(rutina != null) {
                VerRutinaScreen(rutina, this, remitente)
            }
        }
    }

    override fun mostrarEjercicio(ejercicio: Ejercicio){
        val intent = Intent(this, VerEjercicioActivity::class.java)
        intent.putExtra("ejercicio", ejercicio)
        startActivity(intent)
    }

    override fun añadirEjercicio(rutina: Rutina, remitente: Boolean) {
        val intent = Intent(this, añadirEjerciciosActivity::class.java)
        intent.putExtra("rutina", rutina)
        intent.putExtra("remitente", remitente)
        startActivity(intent)
    }

    override fun volverRutina(){
        val intent = Intent(this, RutinasActivity::class.java)
        startActivity(intent)
    }

    override fun volverCalendario() {
        val intent = Intent(this, CalendarioActivity::class.java)
        startActivity(intent)
    }
}