package com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.verEjercicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio
import coil.compose.rememberImagePainter

@Composable
fun VerEjercicioScreen(ejercicio: Ejercicio){

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = ejercicio.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = rememberImagePainter(ejercicio.url),
            contentDescription = ejercicio.name,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .padding(bottom = 8.dp)
                .clip(shape = RoundedCornerShape(4.dp))
        )
        Text(
            text = ejercicio.description,
            fontSize = 16.sp
        )
    }

}