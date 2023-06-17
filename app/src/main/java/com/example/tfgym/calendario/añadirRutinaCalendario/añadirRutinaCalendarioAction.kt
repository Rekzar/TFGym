package com.example.tfgym.calendario.añadirRutinaCalendario

import com.example.tfgym.principal.ui.rutinas.Rutina

interface añadirRutinaCalendarioAction {
    fun añadirRutina(rutina: Rutina, currentDay: String)
    fun volverCalendario()
}