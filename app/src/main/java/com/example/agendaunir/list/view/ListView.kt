package com.example.agendaunir.list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agendaunir.main.view.ShowListEventButton
import com.example.agendaunir.main.view.TaskList
import com.example.agendaunir.model.EventViewModel

@Composable
fun ListView(
    eventViewModel: EventViewModel,
    onClickJson: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFE4D9FF),
            )
            .padding(top = 30.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp) ,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Eventos",
                color = Color(0xFF30343F),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.weight(1f))
           Box()
            {
                Button(
                    onClick = onClickJson,
                    modifier = Modifier
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF273469), // Color de fondo
                        contentColor = Color.White // Color del texto
                    )
                ) {
                    Text(
                        text = "JSON",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        TaskList(viewModel = eventViewModel)
    }
}