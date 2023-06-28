package com.example.tfgym.principal.ui.rutinas.ejerciciosRutina

data class Ejercicio(
    val name: String,
    val description: String,
    val url: String
) : java.io.Serializable{
    constructor(): this("","","")
}
