package com.equipo.proyecto24022026.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.equipo.proyecto24022026.data.model.ProfessorWithCourses
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDao {

    @Transaction
    @Query("SELECT * FROM professors ORDER BY name ASC")
    fun getProfessorsWithCourses(): Flow<List<ProfessorWithCourses>>

    @Transaction
    @Query("SELECT * FROM professors WHERE id = :professorId")
    fun getProfessorWithCourses(professorId: Int): Flow<ProfessorWithCourses?>
}
