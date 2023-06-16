package com.example.tfgym.nutricion.verAlimento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.tfgym.nutricion.Alimento


class AlimentoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val alimento = intent.getSerializableExtra("alimento") as? Alimento
        if (alimento!=null){
            setContent{
                AlimentoScreen(alimento)
            }
        }
    }
}