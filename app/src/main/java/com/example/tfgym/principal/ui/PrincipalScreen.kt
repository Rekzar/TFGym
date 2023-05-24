package com.example.tfgym.principal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter

@Composable
fun MainScreen(
    mainNav: MainNav
) {
    Column {
        Text(
            text = "MainScreen",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(fontSize = 24.sp)
        )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { mainNav.navRutinas() }
                    .background(Blue)
            ) {
                Text(
                    text = "Rutinas",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(color = White, fontSize = 18.sp)
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { mainNav.navNutricion() }
                    .background(Yellow)
            ) {
                Text(
                    text = "Nutrición",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(color = Black, fontSize = 18.sp)
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { mainNav.navCalendario() }
                    .background(Red)
            ) {
                Text(
                    text = "Calendario de ejercicios",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(color = White, fontSize = 18.sp)
                )
            }
        }
}

@Preview
@Composable
fun MainScreenPreview() {
    val mainNav: MainNav = object : MainNav {
        override fun navRutinas() {
            // Implementación de la función navRutinas() para la vista previa
        }

        override fun navNutricion() {
            // Implementación de la función navNutricion() para la vista previa
        }

        override fun navCalendario() {
            // Implementación de la función navCalendario() para la vista previa
        }
    }

    MainScreen(mainNav = mainNav)
}





