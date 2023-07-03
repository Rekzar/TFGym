package com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.verEjercicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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

        Row{
            Column{
                Spacer(modifier = Modifier.height(16.dp))
                Text("Peso en kg: ")
                Spacer(modifier = Modifier.height(40.dp))
                Text("Repeticiones: ")
            }
            Column{
                TextField(value = peso.value,
                    onValueChange = {
                        peso.value = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))

                TextField(value = repeticiones.value,
                    onValueChange = {
                        repeticiones.value = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
            }
        }
        Spacer(Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(onClick = {
                if(peso.value != "" && repeticiones.value != ""){
                    pesoResultado.value = calcularRM(peso.value, repeticiones.value)
                }
            }) {
                Text("Calcular Repetición Máxima")
            }
            Text(text = pesoResultado.value + " Kg")
        }
    }

}

fun calcularRM(peso: String, repeticiones: String): String {
    val pesoResultado = peso.toDouble() / (1.0278 - 0.0278 * repeticiones.toDouble())

    //Se pasa a float para que tenga pocos decimales, ya que las maquinas de gimnasio usan 1 o 2 decimales
    return pesoResultado.toFloat().toString()
}