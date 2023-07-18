package com.example.tfgym.principal.ui.rutinas.mostrarRutina.añadirEjercicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.verEjercicio.VerEjercicioActivity
import com.example.tfgym.principal.ui.rutinas.mostrarRutina.verRutinaActivity

class añadirEjerciciosActivity : AppCompatActivity(), añadirEjerciciosAction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rutina = intent.getSerializableExtra("rutina") as? Rutina
        val remitente = intent.getBooleanExtra("remitente", false)
        if(rutina != null){
            setContent{
                AñadirEjerciciosScreen(rutina, this, remitente)
            }
        }
    }

    override fun mostrarEjercicio(ejercicio: Ejercicio) {
        val intent = Intent(this, VerEjercicioActivity::class.java)
        intent.putExtra("ejercicio", ejercicio)
        startActivity(intent)
    }

    override fun volverVerRutina(rutina: Rutina, remitente: Boolean) {
        val intent = Intent(this, verRutinaActivity::class.java)
        intent.putExtra("rutina", rutina)
        intent.putExtra("remitente", remitente)
        startActivity(intent)
    }
}