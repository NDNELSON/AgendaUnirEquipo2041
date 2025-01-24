package com.example.agendaunir.json.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.agendaunir.model.Event
import com.example.agendaunir.model.EventViewModel
import com.example.agendaunir.model.EventViewModelFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch

class JsonExportActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val eventViewModel: EventViewModel = ViewModelProvider(
            this,
            EventViewModelFactory(application)
        ).get(EventViewModel::class.java)

        lifecycleScope.launch {
            // Obtener los datos de Room
            val events = eventViewModel.getJsonEvents()
            // Convertir a JSON
            val json = convertEventsToJson(events)
            // Configurar la UI para mostrar el JSON
            setContent {
                JsonDisplayScreen(json)
            }
        }
    }

    // Función para convertir la lista de eventos a JSON
    private fun convertEventsToJson(events: List<Event>): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(events)
    }
}