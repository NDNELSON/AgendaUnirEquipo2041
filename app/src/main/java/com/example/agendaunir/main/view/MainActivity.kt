package com.example.agendaunir.main.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.agendaunir.R
import com.example.agendaunir.form.view.EventFormActivity
import com.example.agendaunir.list.view.EventListActivity
import com.example.agendaunir.model.EventViewModel
import com.example.agendaunir.model.EventViewModelFactory
import com.example.agendaunir.ui.theme.AgendaUnirTheme
import com.google.accompanist.drawablepainter.rememberDrawablePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val eventViewModel: EventViewModel = ViewModelProvider(
                this,
                EventViewModelFactory(application) // Pasa el contexto de la aplicación
            ).get(EventViewModel::class.java)
            AgendaUnirTheme {

                HomeScreen(
                    eventViewModel = eventViewModel,
                    onMoreClick = {
                        openCompleteList()
                    },
                    onFabClick = {
                    openEventForm()
                })
            }
        }
    }

    private fun openEventForm() {
        val intent = Intent(this, EventFormActivity::class.java)
        startActivity(intent)
    }

    private fun openCompleteList() {
        val intent = Intent(this, EventListActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun HomeScreen(onFabClick: () -> Unit,
               onMoreClick: () -> Unit,
               eventViewModel: EventViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFAFAFF))

        ) {
            // Header
            Header()
            Spacer(modifier = Modifier.height(16.dp))
            // Task List
            BottomView(
                eventViewModel = eventViewModel,
                onClick = onMoreClick)
        }
        FloatingActionButton(
            onClick = onFabClick,
            modifier = Modifier
                .align(Alignment.BottomCenter) // Posiciona el FAB en la esquina inferior derecha
                .padding(16.dp), // Espaciado del FAB
        ) {
            val svgDrawable = rememberDrawablePainter(
                drawable = androidx.appcompat.content.res.AppCompatResources.getDrawable(
                    LocalContext.current,
                    R.mipmap.addicon // Reemplaza con el ID del recurso SVG
                )
            )
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = svgDrawable,
                contentDescription = "Add Event"
            )
        }
    }

}