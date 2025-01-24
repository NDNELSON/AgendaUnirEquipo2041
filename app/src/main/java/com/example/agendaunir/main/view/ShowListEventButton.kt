package com.example.agendaunir.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.agendaunir.R


@Composable
fun ShowListEventButton(onClick: () -> Unit) {
    Button(
        //modifier = Modifier.padding(8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF273469)),
    ) {
        Image(
            painter = painterResource(id = R.mipmap.list),
            contentDescription = "Button Image",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ver Mas", color = Color.White)
    }
}