package com.example.tfgym.calendario

import com.example.tfgym.principal.ui.rutinas.Rutina

interface CalendarioAction {
    fun añadirRutina(currentDay: String)
    fun mostrarRutina(rutina: Rutina)
    fun eliminarRutina(rutina: Rutina, currentDay: String)
}