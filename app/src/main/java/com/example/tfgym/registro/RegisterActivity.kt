package com.example.tfgym.registro

import RegisterScreen
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tfgym.R
import com.example.tfgym.principal.ui.PrincipalActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : ComponentActivity(), RegisterActions {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            setTheme(R.style.Theme_TFGym)
            RegisterScreen(this)
            }
        }

    override fun registerUser(email: String, password: String, confirmPassword: String) {

        if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(this, "Todos los campos han de estar rellenos", Toast.LENGTH_SHORT).show()
        }
        else if (password != confirmPassword) { // Verificar si las contraseñas ingresadas coinciden
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        }
        else{ // Registrar al usuario en Firebase Authentication
            mAuth = FirebaseAuth.getInstance()
            database = FirebaseDatabase.getInstance().getReference()

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) { // El usuario ha sido registrado exitosamente
                        val map: MutableMap<String, Any> = HashMap()
                        map.put("email", email)
                        map.put("password", password)

                        val id: String = mAuth.currentUser!!.uid
                        database.child("Users").child(id).setValue(map).addOnCompleteListener {
                            if(it.isSuccessful){ //Los datos han sido creados correctamente
                                Toast.makeText(this, "Se pudieron crear los datos correctamente en la base de datos", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                Toast.makeText(this, "No se pudieron crear los datos correctamente en la base de datos", Toast.LENGTH_SHORT).show()
                            }
                        }
                        navToMain()
                    } else {
                        // Se ha producido un error al registrar al usuario
                        Toast.makeText(this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    override fun navToMain() {
        val intent= Intent(this, PrincipalActivity::class.java)
        startActivity(intent)
        finish()
    }
}