package com.equipo.proyecto24022026.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.equipo.proyecto24022026.data.dao.CourseDao
import com.equipo.proyecto24022026.data.dao.ProfessorDao
import com.equipo.proyecto24022026.data.dao.SchoolDao
import com.equipo.proyecto24022026.data.database.SchoolDatabase
import com.equipo.proyecto24022026.data.model.Course
import com.equipo.proyecto24022026.data.model.Professor
import com.equipo.proyecto24022026.data.model.ProfessorWithCourses
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SchoolViewModel(application: Application) : AndroidViewModel(application) {

    private val db = SchoolDatabase.getDatabase(application)
    private val professorDao: ProfessorDao = db.professorDao()
    private val courseDao: CourseDao = db.courseDao()
    private val schoolDao: SchoolDao = db.schoolDao()

    val professors: StateFlow<List<Professor>> = professorDao.getAllProfessors()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allCourses: StateFlow<List<Course>> = courseDao.getAllCourses()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val professorsWithCourses: StateFlow<List<ProfessorWithCourses>> = schoolDao.getProfessorsWithCourses()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getCoursesByProfessor(professorId: Int): Flow<List<Course>> {
        return courseDao.getCoursesByProfessor(professorId)
    }

    fun getProfessorWithCourses(professorId: Int): Flow<ProfessorWithCourses?> {
        return schoolDao.getProfessorWithCourses(professorId)
    }

    suspend fun getProfessorById(id: Int): Professor? = professorDao.getProfessorById(id)

    suspend fun getCourseById(id: Int): Course? = courseDao.getCourseById(id)

    fun addProfessor(professor: Professor) = viewModelScope.launch {
        professorDao.insertProfessor(professor)
    }

    fun updateProfessor(professor: Professor) = viewModelScope.launch {
        professorDao.updateProfessor(professor)
    }

    fun deleteProfessor(professor: Professor) = viewModelScope.launch {
        professorDao.deleteProfessor(professor)
    }

    fun addCourse(course: Course) = viewModelScope.launch {
        courseDao.insertCourse(course)
    }

    fun updateCourse(course: Course) = viewModelScope.launch {
        courseDao.updateCourse(course)
    }

    fun deleteCourse(course: Course) = viewModelScope.launch {
        courseDao.deleteCourse(course)
    }
}
