import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.tfgym.calendario.CalendarioAction
import com.example.tfgym.principal.ui.rutinas.Rutina
import com.example.tfgym.principal.ui.rutinas.obtenerRutinas
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarioScreen(calendarioAction: CalendarioAction?){

    val listaRutinas = remember { mutableStateListOf<Rutina>() }

    obtenerRutinas(listaRutinas)

    val rutinasDia = remember { mutableStateListOf<Rutina>() }

    val currentDay = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "TFGym")},
                navigationIcon = {
                    IconButton(onClick = { calendarioAction?.volverPrincipal() }){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(modifier = Modifier.fillMaxWidth(), factory = { CalendarView(it) }, update = {
                it.setOnDateChangeListener { calendarView, year, month, day ->
                    val selectedDate =
                        LocalDate.of(year, month + 1, day) // +1 porque en Java los meses empiezan en 0
                    currentDay.value = mapDayOfWeek(selectedDate.dayOfWeek.value)
                    rutinasDia.clear()
                    for (rutina in listaRutinas) {
                        if (rutina.selectedDays.contains(currentDay.value)) {
                            rutinasDia.add(rutina)
                        }
                    }
                }
            })
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Espacio vacío en la parte superior
                item { Spacer(modifier = Modifier.height(16.dp)) }

                // Lista de rutinas
                items(rutinasDia) { rutina ->
                    RutinaItem(rutina, calendarioAction, rutinasDia, currentDay.value)
                }

                item{ Button(onClick = {
                    calendarioAction?.añadirRutina(currentDay.value)
                }) {
                    Text("Añadir nueva rutina")
                }}

                // Espacio vacío al final de la lista
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun RutinaItem(rutina: Rutina, calendarioAction: CalendarioAction?, rutinasDia: SnapshotStateList<Rutina>, currentDay: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
    ) {
        Text(
            text = rutina.nombreRutina,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(16.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        ){
            OutlinedButton(onClick = {
                calendarioAction?.mostrarRutina(rutina)
            }) {
                Text(text = "Ver rutina")
            }
            OutlinedButton(onClick = {
                rutinasDia.remove(rutina)
                calendarioAction?.eliminarRutina(rutina, currentDay)
            }) {
                Text(text = "Eliminar rutina")
            }
        }
    }
}

//Función para convertir el día a String
fun mapDayOfWeek(dayOfWeek: Int): String{
    return when (dayOfWeek) {
        1 -> "L"
        2 -> "M"
        3 -> "X"
        4 -> "J"
        5-> "V"
        6 -> "S"

        else -> { "D" }
    }
}