package com.example.agendaunir.form.view
import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.agendaunir.model.Event
import java.text.SimpleDateFormat
import java.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Locale
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.agendaunir.components.CustomButton
import com.example.agendaunir.components.CustomTextField
import com.example.agendaunir.model.EventViewModel
import com.example.agendaunir.model.EventViewModelFactory
import com.example.agendaunir.ui.theme.AgendaUnirTheme

class EventFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val eventViewModel: EventViewModel = ViewModelProvider(
                this,
                EventViewModelFactory(application) // Pasa el contexto de la aplicación
            ).get(EventViewModel::class.java)

            AgendaUnirTheme {
                EventFormScreen(
                    onSave = { event ->
                        eventViewModel.insert(event)
                        finish() // Cierra la actividad después de guardar
                    }
                )
            }
        }
    }
}

@Composable
fun EventFormScreen(onSave: (Event) -> Unit) {
    // Estados para los campos del formulario
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("Tarea") }
    val eventTypes = listOf("Tarea", "Evento", "Cumpleaños")

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    var selectedDate by remember { mutableStateOf(dateFormat.format(calendar.time)) }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    // Estados para validaciones
    val isNameValid = name.isNotBlank()
    val isDescriptionValid = description.isNotBlank()
    val isDateValid = selectedDate.isNotBlank()
    val isStartTimeValid = startTime.isNotBlank()
    val isEndTimeValid = endTime.isNotBlank()
    val areTimesValid = try {
        if (startTime.isNotBlank() && endTime.isNotBlank()) {
            val startDateTime = timeFormat.parse(startTime)
            val endDateTime = timeFormat.parse(endTime)
            startDateTime.before(endDateTime)
        } else false
    } catch (e: Exception) {
        false
    }

    val isFormValid = isNameValid && isDescriptionValid && isDateValid && isStartTimeValid && isEndTimeValid && areTimesValid
    Box(
        modifier = Modifier
            .background(Color(0xFFE4D9FF))
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = "EVENTO",
                color = Color(0xFF30343F),
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold
            )
            // Campo Nombre
            CustomTextField(
                value = name,
                onValueChange = { name = it },
                label = "Nombre del Evento",
                isError = !isNameValid
            )
            if (!isNameValid) {
                Text(
                    text = "El nombre no puede estar vacío",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp,
                )
            }

            // Campo Descripción
            CustomTextField(
                value = description,
                onValueChange = { description = it },
                label = "Descripción",
                isError = !isDescriptionValid
            )
            if (!isDescriptionValid) {
                Text(
                    text = "La descripción no puede estar vacía",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp,
                )
            }


            ButtonWithArrow(
                text = "Tipo de Evento: $selectedType",
                onClick = {

                },
                options = eventTypes, // Pasa las opciones del menú
                onOptionSelected = { selectedType = it } // Actualiza la selección
            )

            // Selección de fecha con picker
            ButtonWithArrow(
                text = "Seleccionar Fecha: $selectedDate",
                onClick = {
                    val datePickerDialog = DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            calendar.set(year, month, dayOfMonth)
                            selectedDate = dateFormat.format(calendar.time)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePickerDialog.show()
                }
            )

            // Selección de hora de inicio con picker
            ButtonWithArrow(
                text = if (startTime.isBlank()) "Seleccionar Hora de Inicio" else "Hora de Inicio: $startTime",
                onClick = {
                    val timePickerDialog = TimePickerDialog(
                        context,
                        { _, hourOfDay, minute ->
                            startTime = String.format("%02d:%02d", hourOfDay, minute)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    )
                    timePickerDialog.show()
                }
            )

            // Selección de hora de fin con picker
            ButtonWithArrow(
                text = if (endTime.isBlank()) "Seleccionar Hora de Fin" else "Hora de Fin: $endTime",
                onClick = {
                    val timePickerDialog = TimePickerDialog(
                        context,
                        { _, hourOfDay, minute ->
                            endTime = String.format("%02d:%02d", hourOfDay, minute)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    )
                    timePickerDialog.show()
                }
            )
            if (!areTimesValid) {
                Text(
                    text = "La hora de inicio debe ser anterior a la hora de fin",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp,
                )
            }

            CustomButton(
                text = "Guardar",
                onClick = {
                    onSave(
                        Event(
                            name = name,
                            description = description,
                            startDate = "$selectedDate $startTime",
                            endDate = "$selectedDate $endTime",
                            type = selectedType
                        )
                    )
                },
                isEnabled = isFormValid, // Botón habilitado solo si las validaciones son correctas
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            )


        }
    }
}