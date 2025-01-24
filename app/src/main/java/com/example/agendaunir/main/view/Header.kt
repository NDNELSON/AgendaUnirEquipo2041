package com.example.agendaunir.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun Header() {
    // Estado para manejar la fecha y hora actuales
    val currentDateTime = remember { mutableStateOf(getCurrentDateTime()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentDateTime.value = getCurrentDateTime()
            delay(60_000L) // Espera 1 minuto antes de actualizar nuevamente
        }
    }

    val (currentDate, currentTime) = currentDateTime.value // Fecha y hora desglosadas
    val calendar = Calendar.getInstance()

    val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH).toString()
    val month = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())?.uppercase() ?: ""
    val year = calendar.get(Calendar.YEAR).toString()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            // Día de la semana
            Text(
                text = dayOfWeek ?: "N/A",
                color = Color(0xFF30343F),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    // Día del mes y mes en abreviatura
                    Text(
                        text = dayOfMonth,
                        color = Color(0xFF30343F),
                        fontSize = 48.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = month,
                        color = Color(0xFF30343F),
                        fontSize = 48.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                // Año rotado
                Text(
                    text = year,
                    color = Color(0xFF30343F),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.graphicsLayer(
                        rotationZ = -90f
                    )
                )
            }
        }
        // Caja de hora actual
        Column(
            modifier = Modifier
                .padding(30.dp)
                .background(
                    Color(0xFFE4D9FF),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                text = currentTime,
                color = Color(0xFF273469),
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}

// Función auxiliar para obtener la fecha y hora actuales
fun getCurrentDateTime(): Pair<String, String> {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

    return dateFormat.format(calendar.time) to timeFormat.format(calendar.time)
}
