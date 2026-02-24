package com.equipo.proyecto24022026.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.equipo.proyecto24022026.data.model.Course
import com.equipo.proyecto24022026.ui.viewmodel.SchoolViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCourseScreen(
    viewModel: SchoolViewModel,
    navController: NavController,
    professorId: Int,
    courseId: Int? = null
) {
    val isEditing = courseId != null
    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var schedule by remember { mutableStateOf("") }
    var creditsText by remember { mutableStateOf("") }
    var professorName by remember { mutableStateOf("") }

    LaunchedEffect(professorId, courseId) {
        viewModel.getProfessorById(professorId)?.let { professorName = it.name }
        if (isEditing) {
            viewModel.getCourseById(courseId!!)?.let { course ->
                title = course.title
                schedule = course.schedule
                creditsText = course.credits.toString()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Editar curso" else "Agregar curso") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = professorName,
                onValueChange = {},
                readOnly = true,
                label = { Text("Profesor asignado") },
                leadingIcon = { Icon(Icons.Default.School, null) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Nombre del curso") },
                leadingIcon = { Icon(Icons.Default.Book, null) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = schedule,
                onValueChange = { schedule = it },
                label = { Text("Horario") },
                placeholder = { Text("Ej: Lunes y Miercoles 8:00 AM") },
                leadingIcon = { Icon(Icons.Default.Schedule, null) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = creditsText,
                onValueChange = { creditsText = it.filter { ch -> ch.isDigit() } },
                label = { Text("Creditos") },
                leadingIcon = { Icon(Icons.Default.Numbers, null) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val credits = creditsText.toIntOrNull()
                    if (title.isBlank() || schedule.isBlank() || credits == null) {
                        Toast.makeText(context, "Completa titulo, horario y creditos validos", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val course = Course(
                        id = courseId ?: 0,
                        title = title.trim(),
                        schedule = schedule.trim(),
                        credits = credits,
                        professorId = professorId
                    )

                    if (isEditing) viewModel.updateCourse(course) else viewModel.addCourse(course)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar curso")
            }
        }
    }
}
