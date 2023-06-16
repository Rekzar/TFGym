package com.example.tfgym.nutricion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.tfgym.nutricion.verAlimento.AlimentoActivity
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.NutricionScreen

class NutricionActivity : AppCompatActivity(),NutricionAction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutricionScreen(this)
        }
    }

    override fun verAlimento(alimento: Alimento){
        val intent = Intent(this, AlimentoActivity::class.java)
        intent.putExtra("alimento", alimento)
        startActivity(intent)
    }
}
