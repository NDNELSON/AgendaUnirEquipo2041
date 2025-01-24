package com.example.agendaunir.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.agendaunir.R
import com.example.agendaunir.model.Event
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun TaskItem(event: Event,
             onDelete: (Event) -> Unit,
             resetSwipeState: Boolean) {
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val anchors = mapOf(0f to 0, -300f to 1) // Distancia de swipe y su estado

    var showDeleteConfirmation by remember { mutableStateOf(false) } // Controla si se muestra la confirmación

    LaunchedEffect(resetSwipeState) {
        if (resetSwipeState) {
            swipeableState.snapTo(0) // Regresa el swipe al estado inicial
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) }, // Umbral del 30% para activar swipe
                orientation = Orientation.Horizontal
            )
    ) {
        // Fondo de eliminación
        if (swipeableState.currentValue == 1) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red, shape = RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    onClick = {
                        showDeleteConfirmation = true // Muestra el cuadro de confirmación
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Text(
                        text = "Eliminar",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // Contenido del ítem
        Box(
            modifier = Modifier
                .offset { IntOffset(swipeableState.offset.value.toInt(), 0) } // Desplazamiento por swipe
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(getColorForEventType(event.type), shape = RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = event.name,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = getDayAndMonth(event.startDate),
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0XFFEEA0E6))
                            .padding(5.dp)
                    ) {
                        Text(
                            text = getDuration(event.startDate, event.endDate),
                            color = Color(0xFF1E2749),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                                append("Comienza: ")
                            }
                            append(event.startDate.split(" ")[1])
                        },
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                                append("Termina: ")
                            }
                            append(event.endDate.split(" ")[1])
                        },
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = event.description,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Image(
                            painter = getImageForEventType(event.type),
                            contentDescription = "Event Type",
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }
    }

    // Confirmación de eliminación
    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = {
                Text(text = "Eliminar Evento")
            },
            text = {
                Text(text = "¿Estás seguro de que deseas eliminar este evento?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDelete(event) // Elimina el evento
                        showDeleteConfirmation = false // Cierra la confirmación
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteConfirmation = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun getImageForEventType(eventType: String): Painter {
    return when (eventType.lowercase()) {
        "tarea" -> painterResource(id = R.mipmap.tarea)       // Imagen para Tareas
        "evento" -> painterResource(id = R.mipmap.evento)     // Imagen para Eventos
        "cumpleaños" -> painterResource(id = R.mipmap.birthday) // Imagen para Cumpleaños
        else -> painterResource(id = R.mipmap.ic_launcher_round)  // Imagen por defecto
    }
}
// Función auxiliar para calcular la duración de un evento
fun getDuration(startDate: String, endDate: String): String {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return try {
        val start = format.parse(startDate.split(" ")[1])
        val end = format.parse(endDate.split(" ")[1])
        val duration = (end.time - start.time) / 60000 // Duración en minutos
        "$duration Min"
    } catch (e: Exception) {
        "N/A"
    }
}

fun getDayAndMonth(date: String): String {
    val inputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd-MMM", Locale.getDefault())

    return try {
        val date = inputFormat.parse(date)
        outputFormat.format(date).uppercase(Locale.getDefault())
    } catch (e: Exception) {
        "N/A"
    }
}

fun getColorForEventType(eventType: String): Color {
    return when (eventType.lowercase()) {
        "tarea" -> Color(0xFF273469) // Color para Tareas
        "evento" -> Color(0xFF30343F) // Color para Eventos
        "cumpleaños" -> Color(0xFFE42BA0) // Color para Cumpleaños
        else -> Color(0xFFFAFAFF) // Color por defecto
    }
}