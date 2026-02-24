package com.equipo.proyecto24022026.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "professors")
data class Professor(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val specialty: String,
    val email: String = ""
)
