package com.example.tfgym.login

interface LoginActions {
 fun signInWithGoogle()
 fun signInWithEmailAndPassword(email: String, password: String, onLoginSuccessful: () -> Unit)
 fun navRegister()
}