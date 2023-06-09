package com.example.tfgym.principal.ui.rutinas.mostrarRutina

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.EjercicioAction

@Composable
fun verRutinaScreen(rutina: Rutina, ejercicioAction: EjercicioAction?){

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = rutina.nombreRutina, fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

            for (ejercicio in rutina.listaEjercicios) {
                EjercicioItem(ejercicio = ejercicio, ejercicioAction)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }


    @Composable
    fun EjercicioItem(ejercicio: Ejercicio, ejercicioAction: EjercicioAction?) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = ejercicio.name, modifier = Modifier.weight(2f/3))
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painter = rememberImagePainter(ejercicio.url),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = { ejercicioAction?.mostrarEjercicio(ejercicio) }
                    ) {
                        Text(text = "Ver ejercicio")
                    }
                }
            }
        }
    }