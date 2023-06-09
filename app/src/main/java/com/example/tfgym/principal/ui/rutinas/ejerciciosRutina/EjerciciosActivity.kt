package com.example.tfgym.principal.ui.rutinas.ejerciciosRutina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.RutinasActivity
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.verEjercicio.VerEjercicioActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EjerciciosActivity : AppCompatActivity(), EjercicioAction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjerciciosScreen(this)
        }
    }

    override fun mostrarEjercicio(ejercicio: Ejercicio){
        val intent = Intent(this, VerEjercicioActivity::class.java)
        intent.putExtra("ejercicio", ejercicio)
        startActivity(intent)
    }

    override fun crearRutina(listaEjercicios: SnapshotStateList<Ejercicio>, nombreRutina: String) {
        val db = FirebaseFirestore.getInstance()
        val rutinasCollection = db.collection("Rutinas")
        val idUser = FirebaseAuth.getInstance().currentUser?.uid

        if (listaEjercicios.isEmpty()){
            Toast.makeText(this, "La rutina debe contener al menos un ejercicio", Toast.LENGTH_SHORT).show()
        } else if (nombreRutina == ""){
            Toast.makeText(this, "La rutina debe tener un nombre", Toast.LENGTH_SHORT).show()
        } else {
            //Crear el objeto rutina y añadirlo a la base de datos de Firebase
            val rutina = Rutina(listaEjercicios, nombreRutina, idUser, null)
            rutinasCollection.add(rutina)
            //Crear el intent para volver a la pantalla de Rutinas
            val intent = Intent(this, RutinasActivity::class.java)
            startActivity(intent)
        }




    }
}

