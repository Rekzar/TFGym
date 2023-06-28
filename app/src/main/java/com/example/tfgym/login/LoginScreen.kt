import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tfgym.R
import com.example.tfgym.login.LoginActions


@Composable
fun LoginScreen(
    loginActions: LoginActions?
) {
    // Variables para almacenar el correo electrónico y la contraseña del usuario
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hidden by remember { mutableStateOf(true) }
    val context = LocalContext.current

    // Acción que se realizará después de que el inicio de sesión sea exitoso
    val onLoginSuccessful = {
        Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        topBar = {
            // Espacio vacío en la parte superior para dejar espacio para el icono de la aplicación
            Spacer(modifier = Modifier.height(64.dp))
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                // Campo de correo electrónico
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Correo electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Campo de contraseña
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation =
                    if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {// 4
                        IconButton(onClick = { hidden = !hidden }) {
                            val vector = painterResource(//5
                                if (hidden) R.drawable.ojo
                                else R.drawable.invisible
                            )
                            val description = if (hidden) "Ocultar contraseña" else "Revelar contraseña"
                            Icon(painter = vector,
                                contentDescription = description,
                                modifier = Modifier.size(ButtonDefaults.IconSize))
                        }
                    }
                )

                // Botón de inicio de sesión con correo electrónico y contraseña
                Button(
                    onClick = { loginActions?.signInWithEmailAndPassword(email, password, onLoginSuccessful) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(text = "Iniciar sesión")
                }

                // O separador
                Text(
                    text = "O",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                // Botón de inicio de sesión con Google
                Button(onClick = { loginActions?.signInWithGoogle()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)) {
                    Text("Iniciar sesión con Google")
                }

                // Texto para navegar a la pantalla de registro
                Text(
                    text = "¿No tienes una cuenta?",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                TextButton(
                    onClick = {
                        loginActions?.navRegister()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Regístrate")
                }
            }
        }
    )
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen(null)
}


