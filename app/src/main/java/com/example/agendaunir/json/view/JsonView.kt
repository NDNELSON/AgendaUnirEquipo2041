package com.example.agendaunir.json.view
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JsonDisplayScreen(json: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Exportar JSON") }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(36.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Datos en formato JSON:",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                ColoredJsonText(json)
            }
        }
    }
}