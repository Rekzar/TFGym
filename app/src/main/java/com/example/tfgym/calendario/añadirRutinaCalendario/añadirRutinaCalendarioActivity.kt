package com.example.tfgym.calendario.añadirRutinaCalendario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.tfgym.calendario.CalendarioActivity
import com.example.tfgym.principal.ui.rutinas.Rutina
import com.google.firebase.firestore.FirebaseFirestore

class añadirRutinaCalendarioActivity : AppCompatActivity(), añadirRutinaCalendarioAction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentDay = intent.getSerializableExtra("currentDay") as? String
        if(currentDay!=null){
            setContent {
                AñadirRutinaCalendarioScreen(this, currentDay)
            }
        }

    }

    override fun añadirRutina(rutina: Rutina, currentDay: String) {
        rutina.selectedDays.add(currentDay)
        FirebaseFirestore.getInstance().document(rutina.pathDocumento)
            .update("selectedDays", rutina.selectedDays)
            .addOnSuccessListener {
                println("Rutina eliminada correctamente")
            }
            .addOnFailureListener { exception ->
                println("Error al eliminar la rutina: ${exception.message}")
            }
        volverCalendario()
    }

    override fun volverCalendario() {
        val intent = Intent(this, CalendarioActivity::class.java)
        startActivity(intent)
    }
}