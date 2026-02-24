package com.equipo.proyecto24022026.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "courses",
    foreignKeys = [
        ForeignKey(
            entity = Professor::class,
            parentColumns = ["id"],
            childColumns = ["professorId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["professorId"])]
)
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val schedule: String,
    val credits: Int,
    val professorId: Int
)
