package com.equipo.proyecto24022026.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.equipo.proyecto24022026.data.model.Professor
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfessorDao {

    @Query("SELECT * FROM professors ORDER BY name ASC")
    fun getAllProfessors(): Flow<List<Professor>>

    @Query("SELECT * FROM professors WHERE id = :id")
    suspend fun getProfessorById(id: Int): Professor?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfessor(professor: Professor)

    @Update
    suspend fun updateProfessor(professor: Professor)

    @Delete
    suspend fun deleteProfessor(professor: Professor)
}
