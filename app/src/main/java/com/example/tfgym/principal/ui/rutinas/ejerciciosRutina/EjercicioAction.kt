package com.example.tfgym.principal.ui.rutinas.ejerciciosRutina

import androidx.compose.runtime.snapshots.SnapshotStateList

interface EjercicioAction {
    fun mostrarEjercicio(ejercicio: Ejercicio)
    fun crearRutina(listaEjercicios: SnapshotStateList<Ejercicio>, nombreRutina: String, selectedDays: SnapshotStateList<String>)
    fun volverRutina()
}