package com.example.agendaunir.json.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColoredJsonText(json: String) {
    val keyColor = Color(0xFF64B5F6)   // Azul para las claves
    val valueColor = Color(0xFF81C784) // Verde para los valores
    val bracketsColor = Color(0xFFFFA726) // Naranja para llaves y corchetes

    // Expresión regular para separar claves, valores y corchetes
    val regex = """("[^"]*":)|("[^"]*")|[\{\}\[\]]""".toRegex()

    val annotatedString = buildAnnotatedString {
        regex.findAll(json).forEach { matchResult ->
            when {
                matchResult.value.startsWith("\"") && matchResult.value.endsWith("\":") -> {
                    // Clave
                    appendStyledText(matchResult.value, keyColor)
                }
                matchResult.value.startsWith("\"") && matchResult.value.endsWith("\"") -> {
                    // Valor
                    appendStyledText(matchResult.value, valueColor)
                }
                else -> {
                    // Corchetes o llaves
                    appendStyledText(matchResult.value, bracketsColor)
                }
            }
        }
    }

    // Mostrar el JSON formateado con colores
    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(8.dp),
        fontSize = 20.sp
    )
}

// Función auxiliar para añadir texto con estilo
fun AnnotatedString.Builder.appendStyledText(text: String, color: Color) {
    withStyle(style = SpanStyle(color = color)) {
        append(text)
    }
}
