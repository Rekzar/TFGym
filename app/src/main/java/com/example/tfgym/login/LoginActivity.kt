package com.example.tfgym.login

import LoginScreen
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.example.tfgym.R
import com.example.tfgym.principal.ui.PrincipalActivity
import com.example.tfgym.registro.RegisterActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginActivity : AppCompatActivity(), LoginActions {
    private val auth = Firebase.auth
    private val GOOGLE_SIGN_IN_REQUEST_CODE = 1000

    //Variable para almacenar el estado de autenticación del usuario
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            setTheme(R.style.Theme_TFGym)
            // Creamos una variable para almacenar el estado de autenticación
            val isLoggedIn = _isLoggedIn.collectAsState()

            // Si el usuario ya ha iniciado sesión, mostramos la pantalla principal de la aplicación
            if (isLoggedIn.value/* isLoggedIn.value Esto es lo correcto*/) {
                val intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
            } else {
                // Si el usuario aún no ha iniciado sesión, mostramos la pantalla de inicio de sesión
                LoginScreen(loginActions = this, onLoginSuccessful = {
                    // Acción que se realizará después de que el inicio de sesión sea exitoso
                    Toast.makeText(this@LoginActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    //Función que se encarga de iniciar sesión en google y redirigir a la pantalla principal
    override fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    //Función que se encarga de iniciar sesión con un email y una contraseña propios
    override fun signInWithEmailAndPassword(email: String, password: String, onLoginSuccessful: () -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // El inicio de sesión fue exitoso, llama a la función de callback
                    onLoginSuccessful()
                    val intent = Intent(this, PrincipalActivity::class.java)
                    startActivity(intent)
                } else {
                    // El inicio de sesión falló, maneja el error aquí
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun navRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }


}