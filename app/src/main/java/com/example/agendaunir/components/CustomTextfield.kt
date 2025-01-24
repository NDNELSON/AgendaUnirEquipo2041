package com.example.agendaunir.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    borderColor: Color = Color(0xFF30343F),
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(
            label,
            color = Color(0xFF30343F),
            fontSize = 22.sp,
        ) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(70.dp)
            .border(5.dp, borderColor, shape = MaterialTheme.shapes.small),
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color(0xFF30343F),          // Color del texto enfocado
            unfocusedTextColor = Color(0xFF30343F),        // Color del texto desenfocado
            disabledTextColor = Color.Gray,          // Color del texto deshabilitado
            errorTextColor = Color.Red,              // Color del texto en caso de error
            focusedContainerColor = Color.White,     // Fondo blanco enfocado
            unfocusedContainerColor = Color.White,   // Fondo blanco desenfocado
            disabledContainerColor = Color.LightGray,// Fondo cuando está deshabilitado
            errorContainerColor = Color.White,       // Fondo en caso de error
            cursorColor = Color.White,               // Color del cursor
            errorCursorColor = Color.Red,            // Color del cursor en caso de error
            focusedBorderColor = Color.Transparent,         // Borde azul enfocado
            unfocusedBorderColor = Color.Transparent,       // Borde gris desenfocado
            disabledBorderColor = Color.Transparent,   // Borde deshabilitado
            errorBorderColor = Color.Transparent,            // Borde rojo en caso de error
            focusedLabelColor = Color.White,         // Color del label enfocado
            unfocusedLabelColor = Color.White,       // Color del label desenfocado
            disabledLabelColor = Color.Gray,         // Color del label deshabilitado
            errorLabelColor = Color.Red              // Color del label en caso de error

        )
    )
}