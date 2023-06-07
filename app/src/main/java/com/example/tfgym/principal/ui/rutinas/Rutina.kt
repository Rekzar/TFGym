package com.example.tfgym.principal.ui.rutinas

import com.example.tfgym.principal.ui.rutinas.ejerciciosRutina.Ejercicio

class Rutina (
    val listaEjercicios: MutableList<Ejercicio>,
    val nombreRutina: String,
    val idUsuario: String?) : java.io.Serializable{

    constructor(): this(mutableListOf(),"",null)

}