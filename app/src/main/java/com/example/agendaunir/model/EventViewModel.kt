package com.example.agendaunir.model
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EventRepository

    init {
        val database = EventDatabase.getDatabase(application) // Inicializa la base de datos
        val eventDao = database.eventDao()
        repository = EventRepository(eventDao)
    }

    val events:  Flow<List<Event>> = repository.allEvents

    fun insert(event: Event) = viewModelScope.launch {
        repository.insert(event)
    }

    // Método para eliminar un evento directamente
    fun deleteEvent(event: Event) {
        viewModelScope.launch {
            repository.deleteEvent(event)
        }
    }

    suspend fun getJsonEvents() : List<Event> {
        return repository.getAllEvents()
    }
}