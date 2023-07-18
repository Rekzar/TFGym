package com.example.tfgym.principal.ui.rutinas.mostrarRutina

import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio

interface verRutinaAction {
    fun mostrarEjercicio(ejercicio: Ejercicio)
    fun añadirEjercicio(rutina: Rutina, remitente: Boolean)
    fun volverRutina()
    fun volverCalendario()
}