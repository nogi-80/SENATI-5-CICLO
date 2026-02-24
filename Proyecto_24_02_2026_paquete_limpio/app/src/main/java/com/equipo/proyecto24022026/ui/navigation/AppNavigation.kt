package com.equipo.proyecto24022026.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.equipo.proyecto24022026.ui.screens.AddEditCourseScreen
import com.equipo.proyecto24022026.ui.screens.AddEditProfessorScreen
import com.equipo.proyecto24022026.ui.screens.CourseListScreen
import com.equipo.proyecto24022026.ui.screens.ProfessorListScreen
import com.equipo.proyecto24022026.ui.viewmodel.SchoolViewModel

sealed class Screen(val route: String) {
    object Professors : Screen("professors")
    object AddProfessor : Screen("add_professor")
    object EditProfessor : Screen("edit_professor/{professorId}") {
        fun createRoute(professorId: Int) = "edit_professor/$professorId"
    }
    object CoursesByProfessor : Screen("courses/{professorId}") {
        fun createRoute(professorId: Int) = "courses/$professorId"
    }
    object AddCourse : Screen("add_course/{professorId}") {
        fun createRoute(professorId: Int) = "add_course/$professorId"
    }
    object EditCourse : Screen("edit_course/{courseId}/{professorId}") {
        fun createRoute(courseId: Int, professorId: Int) = "edit_course/$courseId/$professorId"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: SchoolViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Professors.route) {
        composable(Screen.Professors.route) {
            ProfessorListScreen(
                viewModel = viewModel,
                onAddProfessor = { navController.navigate(Screen.AddProfessor.route) },
                onEditProfessor = { id -> navController.navigate(Screen.EditProfessor.createRoute(id)) },
                onOpenCourses = { id -> navController.navigate(Screen.CoursesByProfessor.createRoute(id)) }
            )
        }
        composable(Screen.AddProfessor.route) {
            AddEditProfessorScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = Screen.EditProfessor.route,
            arguments = listOf(navArgument("professorId") { type = NavType.IntType })
        ) { backStackEntry ->
            val professorId = backStackEntry.arguments?.getInt("professorId")
            AddEditProfessorScreen(
                viewModel = viewModel,
                navController = navController,
                professorId = professorId
            )
        }
        composable(
            route = Screen.CoursesByProfessor.route,
            arguments = listOf(navArgument("professorId") { type = NavType.IntType })
        ) { backStackEntry ->
            val professorId = backStackEntry.arguments?.getInt("professorId") ?: return@composable
            CourseListScreen(
                professorId = professorId,
                viewModel = viewModel,
                navController = navController,
                onAddCourse = { navController.navigate(Screen.AddCourse.createRoute(professorId)) },
                onEditCourse = { courseId -> navController.navigate(Screen.EditCourse.createRoute(courseId, professorId)) }
            )
        }
        composable(
            route = Screen.AddCourse.route,
            arguments = listOf(navArgument("professorId") { type = NavType.IntType })
        ) { backStackEntry ->
            val professorId = backStackEntry.arguments?.getInt("professorId") ?: return@composable
            AddEditCourseScreen(
                viewModel = viewModel,
                navController = navController,
                professorId = professorId
            )
        }
        composable(
            route = Screen.EditCourse.route,
            arguments = listOf(
                navArgument("courseId") { type = NavType.IntType },
                navArgument("professorId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId")
            val professorId = backStackEntry.arguments?.getInt("professorId") ?: return@composable
            AddEditCourseScreen(
                viewModel = viewModel,
                navController = navController,
                professorId = professorId,
                courseId = courseId
            )
        }
    }
}
