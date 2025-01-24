package com.example.agendaunir.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    backgroundColor: Color = Color(0xFF30343F),
    disabledBackgroundColor: Color = Color.Gray,
    disabledTextColor: Color = Color.LightGray,
    fontSize: TextUnit = 25.sp,
    fontWeight: FontWeight = FontWeight.ExtraBold
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) backgroundColor else disabledBackgroundColor,
            contentColor = if (isEnabled) textColor else disabledTextColor
        )
    ) {
        Text(
            text = text,
            fontWeight = fontWeight,
            fontSize = fontSize
        )
    }
}