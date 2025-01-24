package com.example.agendaunir.main.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.agendaunir.components.TaskItem
import com.example.agendaunir.model.EventViewModel
import kotlinx.coroutines.flow.toList
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TaskList(viewModel: EventViewModel) {
    // Obtener la lista de eventos desde el ViewModel
    val events by viewModel.events.collectAsState(initial = emptyList())
    var resetSwipeState by remember { mutableStateOf(false) }
    if (events.isEmpty()) {
        // Mostrar mensaje cuando no hay eventos
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay eventos disponibles",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre ítems
        ) {
            items(events) { event ->
                TaskItem(event, onDelete = {
                    viewModel.deleteEvent(event)
                    resetSwipeState = true
                },
                    resetSwipeState = resetSwipeState)
            }
        }

        // Reinicia el estado después de eliminar
        LaunchedEffect(events.size) {
            resetSwipeState = false
        }
    }
}
