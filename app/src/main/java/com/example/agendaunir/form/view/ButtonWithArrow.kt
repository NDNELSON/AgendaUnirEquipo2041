package com.example.agendaunir.form.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonWithArrow(
    text: String,
    onClick: () -> Unit, // Mantiene la funcionalidad actual del botón
    options: List<String>? = null, // Opciones del menú desplegable, opcional
    onOptionSelected: ((String) -> Unit)? = null // Acción al seleccionar una opción, opcional
) {
    var expanded by remember { mutableStateOf(false) } // Controla si el menú está abierto

    Box(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {
                onClick() // Ejecuta la funcionalidad existente
                if (options != null) {
                    expanded = !expanded // Abre/cierra el menú si hay opciones
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    fontSize = 20.sp
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Flecha",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Solo muestra el DropdownMenu si hay opciones
        if (options != null && onOptionSelected != null) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false } // Cierra el menú al hacer clic fuera
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onOptionSelected(option) // Notifica la opción seleccionada
                            expanded = false // Cierra el menú
                        }
                    )
                }
            }
        }
    }
}
