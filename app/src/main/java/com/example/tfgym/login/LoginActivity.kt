package com.example.tfgym.login

import LoginScreen
import android.app.Activity
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
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow


class LoginActivity : AppCompatActivity(), LoginActions {
    private val auth = Firebase.auth
    private val GOOGLE_SIGN_IN_REQUEST_CODE = 1000

    //Variable para almacenar el estado de autenticación del usuario
    private val _isLoggedIn = MutableStateFlow(false)

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
                LoginScreen(loginActions = this)
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
        mGoogleSignInClient.signOut()
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Inicio de sesión con Google exitoso, obtén la credencial de acceso
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken

                // Autenticar en Firebase con la credencial de acceso de Google
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            // Inicio de sesión en Firebase con Google exitoso
                            val user = FirebaseAuth.getInstance().currentUser
                            val intent = Intent(this, PrincipalActivity::class.java)
                            startActivity(intent)
                        } else {
                            // Error en el inicio de sesión en Firebase con Google
                            Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show()
                        }
                    }
            } catch (e: ApiException) {
                // Error en el inicio de sesión con Google
                Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Función que se encarga de iniciar sesión con un email y una contraseña propios
    override fun signInWithEmailAndPassword(email: String, password: String, onLoginSuccessful: () -> Unit) {
        if(email.isNotEmpty() && password.isNotEmpty()){
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
    }

    override fun navRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

}