package com.example.agendaunir.list.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.agendaunir.form.view.EventFormActivity
import com.example.agendaunir.json.view.JsonExportActivity
import com.example.agendaunir.main.view.HomeScreen
import com.example.agendaunir.model.EventViewModel
import com.example.agendaunir.model.EventViewModelFactory
import com.example.agendaunir.ui.theme.AgendaUnirTheme

class EventListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val eventViewModel: EventViewModel = ViewModelProvider(
                this,
                EventViewModelFactory(application) // Pasa el contexto de la aplicación
            ).get(EventViewModel::class.java)
            AgendaUnirTheme {

                ListView(eventViewModel
                , onClickJson = {
                    openJsonActivity()
                    })

            }
        }
    }

    private fun openJsonActivity() {
        val intent = Intent(this, JsonExportActivity::class.java)
        startActivity(intent)
    }

}