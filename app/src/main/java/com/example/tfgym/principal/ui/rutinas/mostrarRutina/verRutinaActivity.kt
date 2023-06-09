package com.example.tfgym.principal.ui.rutinas.mostrarRutina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.EjercicioAction
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.verEjercicio.VerEjercicioActivity

class verRutinaActivity : AppCompatActivity(), EjercicioAction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rutina = intent.getSerializableExtra("rutina") as? Rutina
        setContent {
            if(rutina != null) {
                verRutinaScreen(rutina, this)
            }
        }
    }

    override fun mostrarEjercicio(ejercicio: Ejercicio){
        val intent = Intent(this, VerEjercicioActivity::class.java)
        intent.putExtra("ejercicio", ejercicio)
        startActivity(intent)
    }

    override fun crearRutina(listaEjercicios: SnapshotStateList<Ejercicio>, nombreRutina: String) {
        TODO("Not yet implemented")
    }
}