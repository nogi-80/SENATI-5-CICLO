package com.equipo.proyecto24022026.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class ProfessorWithCourses(
    @Embedded val professor: Professor,
    @Relation(
        parentColumn = "id",
        entityColumn = "professorId"
    )
    val courses: List<Course>
)
