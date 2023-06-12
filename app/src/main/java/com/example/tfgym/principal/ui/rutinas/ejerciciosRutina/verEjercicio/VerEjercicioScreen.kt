package com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.verEjercicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio
import coil.compose.rememberImagePainter

@Composable
fun VerEjercicioScreen(ejercicio: Ejercicio){
    // Estado para almacenar el peso del 1RM
    val peso = remember { mutableStateOf("") }

    // Estado para almacenar las repeticiones del 1RM
    val repeticiones = remember { mutableStateOf("") }

    //Estado para almacenar el resultado del peso
    val pesoResultado = remember { mutableStateOf("") }


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

        TextField(value = peso.value + " Kg",
            onValueChange = {
                peso.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))

        TextField(value = repeticiones.value,
            onValueChange = {
                repeticiones.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))

        Row{
            OutlinedButton(onClick = { pesoResultado.value = calcularRM(peso.value, repeticiones.value) }) {
                Text("Calcular Repetición Máxima")
            }
            Text(text = pesoResultado.value + " Kg")
        }
    }

}

fun calcularRM(peso: String, repeticiones: String): String {
    var pesoResultado = 0.0

    pesoResultado = peso.toDouble() / (1.0278 - 0.0278 * repeticiones.toDouble())

    return pesoResultado.toString()
}