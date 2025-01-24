package com.example.agendaunir.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agendaunir.model.EventViewModel

@Composable
fun BottomView(
    eventViewModel: EventViewModel,
    onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFE4D9FF),
                shape = RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 40.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
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
                text = "Próximo",
                color = Color(0xFF30343F),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.weight(1f))
            Box()
            {
                ShowListEventButton(onClick = onClick)
            }
        }
        TaskList(viewModel = eventViewModel)
    }
}