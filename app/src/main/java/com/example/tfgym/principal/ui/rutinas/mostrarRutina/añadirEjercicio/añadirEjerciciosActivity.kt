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
        if(rutina != null){
            setContent{
                añadirEjerciciosScreen(rutina, this)
            }
        }
    }

    override fun mostrarEjercicio(ejercicio: Ejercicio) {
        val intent = Intent(this, VerEjercicioActivity::class.java)
        intent.putExtra("ejercicio", ejercicio)
        startActivity(intent)
    }

    override fun volverRutina(rutina: Rutina) {
        val intent = Intent(this, verRutinaActivity::class.java)
        intent.putExtra("rutina", rutina)
        startActivity(intent)
    }
}