package com.example.tfgym.calendario


import CalendarioScreen
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.tfgym.calendario.añadirRutinaCalendario.añadirRutinaCalendarioActivity
import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.mostrarRutina.verRutinaActivity
import com.google.firebase.firestore.FirebaseFirestore


class CalendarioActivity : AppCompatActivity(), CalendarioAction {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarioScreen(this)
        }
    }

    override fun añadirRutina(currentDay: String) {
        val intent = Intent(this, añadirRutinaCalendarioActivity::class.java)
        intent.putExtra("currentDay", currentDay)
        startActivity(intent)
    }

    override fun mostrarRutina(rutina: Rutina) {
        val intent = Intent(this, verRutinaActivity::class.java)
        intent.putExtra("rutina", rutina)
        startActivity(intent)
    }

    override fun eliminarRutina(rutina: Rutina, currentDay: String) {
        rutina.selectedDays.remove(currentDay)
        FirebaseFirestore.getInstance().document(rutina.pathDocumento)
            .update("selectedDays", rutina.selectedDays)
            ?.addOnSuccessListener {
                println("Rutina eliminada correctamente")
            }
            ?.addOnFailureListener { exception ->
                println("Error al eliminar la rutina: ${exception.message}")
            }
    }
}

