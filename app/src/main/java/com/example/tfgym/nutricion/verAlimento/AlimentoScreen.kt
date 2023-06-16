package com.example.tfgym.nutricion.verAlimento

import com.example.tfgym.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.tfgym.nutricion.Alimento

@Composable
fun AlimentoScreen(alimento: Alimento) {
    val total = alimento.hidratos + alimento.grasa + alimento.proteina

    val porcentajeHidratos = alimento.hidratos / total * 100
    val porcentajeGrasa = alimento.grasa / total * 100
    val porcentajeProteinas = alimento.proteina / total * 100

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Column {
                    Text(
                        text = alimento.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(text = "Por cada ración de 100g:")
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = alimento.calorias.toString() + " Kcal")
                }
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = rememberImagePainter(alimento.url),
                    contentDescription = alimento.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(120.dp)
                        .padding(bottom = 8.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasSize = size.minDimension
                    val radius = canvasSize / 2
                    val centerX = size.width / 2
                    val centerY = size.height / 2

                    val startAngle = 0f

                    drawArc(
                        color = Blue,
                        startAngle = startAngle,
                        sweepAngle = porcentajeHidratos * 3.6f,
                        useCenter = true,
                        topLeft = Offset(centerX - radius, centerY - radius),
                        size = Size(canvasSize, canvasSize)
                    )

                    drawArc(
                        color = Red,
                        startAngle = startAngle + porcentajeHidratos * 3.6f,
                        sweepAngle = porcentajeGrasa * 3.6f,
                        useCenter = true,
                        topLeft = Offset(centerX - radius, centerY - radius),
                        size = Size(canvasSize, canvasSize)
                    )

                    drawArc(
                        color = Green,
                        startAngle = startAngle + (porcentajeHidratos + porcentajeGrasa) * 3.6f,
                        sweepAngle = porcentajeProteinas * 3.6f,
                        useCenter = true,
                        topLeft = Offset(centerX - radius, centerY - radius),
                        size = Size(canvasSize, canvasSize)
                    )
                }
            }

            NutrienteItem(name = "Hidratos: " + alimento.hidratos.toString() + "g", color = Blue, icon = R.drawable.ic_blue_square)
            NutrienteItem(name = "Grasa: " + alimento.grasa.toString() + "g", color = Red, icon = R.drawable.ic_red_square)
            NutrienteItem(name = "Proteína: " + alimento.proteina.toString() + "g", color = Green, icon = R.drawable.ic_green_square)
        }
    }
}



@Composable
fun NutrienteItem(name: String,color: androidx.compose.ui.graphics.Color, icon: Int){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .padding(end = 8.dp),
            tint = color
        )
        Text(text = name)
    }
}