package com.example.agendaunir.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Clave primaria
    val name: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val type: String
)
