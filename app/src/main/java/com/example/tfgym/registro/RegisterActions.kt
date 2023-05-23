package com.example.tfgym.registro

interface RegisterActions {
 fun registerUser(email: String, password: String, confirmPassword: String)
 fun navToMain()
}