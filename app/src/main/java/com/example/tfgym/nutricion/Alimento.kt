package com.example.tfgym.nutricion

data class Alimento(
    val name: String,
    val calorias: Float,
    val grasa: Float,
    val hidratos: Float,
    val proteina: Float,
    val url: String
): java.io.Serializable {
    constructor(): this("",0f,0f, 0f, 0f, "")
}