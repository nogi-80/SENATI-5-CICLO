# Explicacion linea por linea (archivos clave)

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/MainActivity.kt`
1. `package com.equipo.proyecto24022026` — Define el paquete del archivo.
2. Línea en blanco — Separación visual.
3. `import android.os.Bundle` — Importa la clase `Bundle` para el ciclo de vida.
4. `import androidx.activity.ComponentActivity` — Importa la actividad base de Compose.
5. `import androidx.activity.compose.setContent` — Permite setear UI Compose en la actividad.
6. `import androidx.activity.enableEdgeToEdge` — Habilita renderizado edge-to-edge.
7. `import com.equipo.proyecto24022026.ui.navigation.AppNavigation` — Importa la navegación principal.
8. `import com.equipo.proyecto24022026.ui.theme.ContactsAppTheme` — Importa el tema visual.
9. Línea en blanco — Separación visual.
10. `class MainActivity : ComponentActivity() {` — Declara la actividad principal.
11. `override fun onCreate(savedInstanceState: Bundle?) {` — Inicia el ciclo de vida de la actividad.
12. `super.onCreate(savedInstanceState)` — Llama la implementación base.
13. `enableEdgeToEdge()` — Activa el modo edge-to-edge.
14. `setContent {` — Define el contenido Compose.
15. `ContactsAppTheme {` — Aplica el tema a la UI.
16. `AppNavigation()` — Carga el grafo de navegación.
17. `}` — Cierra el bloque del tema.
18. `}` — Cierra el bloque de `setContent`.
19. `}` — Cierra `onCreate`.
20. `}` — Cierra la clase.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/data/model/Professor.kt`
1. `package com.equipo.proyecto24022026.data.model` — Define el paquete del modelo.
2. Línea en blanco — Separación visual.
3. `import androidx.room.Entity` — Importa anotación de entidad Room.
4. `import androidx.room.PrimaryKey` — Importa anotación de clave primaria.
5. Línea en blanco — Separación visual.
6. `@Entity(tableName = "professors")` — Declara la tabla `professors`.
7. `data class Professor(` — Define el modelo `Professor`.
8. `@PrimaryKey(autoGenerate = true)` — Marca `id` como clave primaria autogenerada.
9. `val id: Int = 0,` — Id del profesor (por defecto 0 para insertar).
10. `val name: String,` — Nombre del profesor.
11. `val specialty: String,` — Especialidad del profesor.
12. `val email: String = ""` — Email opcional (vacío por defecto).
13. `)` — Cierra la clase de datos.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/data/model/Course.kt`
1. `package com.equipo.proyecto24022026.data.model` — Define el paquete del modelo.
2. Línea en blanco — Separación visual.
3. `import androidx.room.Entity` — Importa anotación de entidad Room.
4. `import androidx.room.ForeignKey` — Importa relación de clave foránea.
5. `import androidx.room.Index` — Importa índice para consultas.
6. `import androidx.room.PrimaryKey` — Importa anotación de clave primaria.
7. Línea en blanco — Separación visual.
8. `@Entity(` — Inicia configuración de la entidad Room.
9. `tableName = "courses",` — Nombre de la tabla `courses`.
10. `foreignKeys = [` — Declara claves foráneas.
11. `ForeignKey(` — Inicia la configuración de la clave foránea.
12. `entity = Professor::class,` — Apunta a la entidad `Professor`.
13. `parentColumns = ["id"],` — Columna padre `id`.
14. `childColumns = ["professorId"],` — Columna hija `professorId`.
15. `onDelete = ForeignKey.CASCADE` — Borra cursos si se borra el profesor.
16. `)` — Cierra la clave foránea.
17. `],` — Cierra la lista de claves foráneas.
18. `indices = [Index(value = ["professorId"])]` — Índice para acelerar consultas por profesor.
19. `)` — Cierra la anotación `@Entity`.
20. `data class Course(` — Define el modelo `Course`.
21. `@PrimaryKey(autoGenerate = true)` — Marca `id` como clave primaria autogenerada.
22. `val id: Int = 0,` — Id del curso (por defecto 0 para insertar).
23. `val title: String,` — Título del curso.
24. `val schedule: String,` — Horario del curso.
25. `val credits: Int,` — Créditos del curso.
26. `val professorId: Int` — Id del profesor asignado.
27. `)` — Cierra la clase de datos.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/data/dao/ProfessorDao.kt`
1. `package com.equipo.proyecto24022026.data.dao` — Define el paquete del DAO.
2. Línea en blanco — Separación visual.
3. `import androidx.room.Dao` — Importa la anotación DAO.
4. `import androidx.room.Delete` — Importa operación de borrado.
5. `import androidx.room.Insert` — Importa operación de inserción.
6. `import androidx.room.OnConflictStrategy` — Importa estrategia de conflicto.
7. `import androidx.room.Query` — Importa consultas SQL.
8. `import androidx.room.Update` — Importa operación de actualización.
9. `import com.equipo.proyecto24022026.data.model.Professor` — Importa el modelo `Professor`.
10. `import kotlinx.coroutines.flow.Flow` — Importa `Flow` para datos reactivos.
11. Línea en blanco — Separación visual.
12. `@Dao` — Marca la interfaz como DAO.
13. `interface ProfessorDao {` — Declara el DAO de profesores.
14. Línea en blanco — Separación visual.
15. `@Query("SELECT * FROM professors ORDER BY name ASC")` — Consulta todos los profesores ordenados por nombre.
16. `fun getAllProfessors(): Flow<List<Professor>>` — Retorna un flujo reactivo de profesores.
17. Línea en blanco — Separación visual.
18. `@Query("SELECT * FROM professors WHERE id = :id")` — Consulta un profesor por id.
19. `suspend fun getProfessorById(id: Int): Professor?` — Función suspendida para obtener un profesor.
20. Línea en blanco — Separación visual.
21. `@Insert(onConflict = OnConflictStrategy.REPLACE)` — Inserta o reemplaza si hay conflicto.
22. `suspend fun insertProfessor(professor: Professor)` — Inserta un profesor.
23. Línea en blanco — Separación visual.
24. `@Update` — Marca operación de actualización.
25. `suspend fun updateProfessor(professor: Professor)` — Actualiza un profesor.
26. Línea en blanco — Separación visual.
27. `@Delete` — Marca operación de borrado.
28. `suspend fun deleteProfessor(professor: Professor)` — Elimina un profesor.
29. `}` — Cierra la interfaz.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/data/dao/CourseDao.kt`
1. `package com.equipo.proyecto24022026.data.dao` — Define el paquete del DAO.
2. Línea en blanco — Separación visual.
3. `import androidx.room.Dao` — Importa la anotación DAO.
4. `import androidx.room.Delete` — Importa operación de borrado.
5. `import androidx.room.Insert` — Importa operación de inserción.
6. `import androidx.room.OnConflictStrategy` — Importa estrategia de conflicto.
7. `import androidx.room.Query` — Importa consultas SQL.
8. `import androidx.room.Update` — Importa operación de actualización.
9. `import com.equipo.proyecto24022026.data.model.Course` — Importa el modelo `Course`.
10. `import kotlinx.coroutines.flow.Flow` — Importa `Flow` para datos reactivos.
11. Línea en blanco — Separación visual.
12. `@Dao` — Marca la interfaz como DAO.
13. `interface CourseDao {` — Declara el DAO de cursos.
14. Línea en blanco — Separación visual.
15. `@Query("SELECT * FROM courses ORDER BY title ASC")` — Consulta todos los cursos ordenados por título.
16. `fun getAllCourses(): Flow<List<Course>>` — Retorna flujo de cursos.
17. Línea en blanco — Separación visual.
18. `@Query("SELECT * FROM courses WHERE professorId = :professorId ORDER BY title ASC")` — Consulta cursos por profesor.
19. `fun getCoursesByProfessor(professorId: Int): Flow<List<Course>>` — Retorna cursos de un profesor.
20. Línea en blanco — Separación visual.
21. `@Query("SELECT * FROM courses WHERE id = :id")` — Consulta curso por id.
22. `suspend fun getCourseById(id: Int): Course?` — Obtiene un curso por id.
23. Línea en blanco — Separación visual.
24. `@Insert(onConflict = OnConflictStrategy.REPLACE)` — Inserta o reemplaza en conflicto.
25. `suspend fun insertCourse(course: Course)` — Inserta un curso.
26. Línea en blanco — Separación visual.
27. `@Update` — Marca operación de actualización.
28. `suspend fun updateCourse(course: Course)` — Actualiza un curso.
29. Línea en blanco — Separación visual.
30. `@Delete` — Marca operación de borrado.
31. `suspend fun deleteCourse(course: Course)` — Elimina un curso.
32. `}` — Cierra la interfaz.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/data/database/SchoolDatabase.kt`
1. `package com.equipo.proyecto24022026.data.database` — Define el paquete de base de datos.
2. Línea en blanco — Separación visual.
3. `import android.content.Context` — Importa `Context` para construir la BD.
4. `import androidx.room.Database` — Importa anotación `@Database`.
5. `import androidx.room.Room` — Importa el builder de Room.
6. `import androidx.room.RoomDatabase` — Clase base de Room.
7. `import com.equipo.proyecto24022026.data.dao.CourseDao` — Importa DAO de cursos.
8. `import com.equipo.proyecto24022026.data.dao.ProfessorDao` — Importa DAO de profesores.
9. `import com.equipo.proyecto24022026.data.dao.SchoolDao` — Importa DAO de relaciones.
10. `import com.equipo.proyecto24022026.data.model.Course` — Importa entidad `Course`.
11. `import com.equipo.proyecto24022026.data.model.Professor` — Importa entidad `Professor`.
12. Línea en blanco — Separación visual.
13. `@Database(entities = [Professor::class, Course::class], version = 1, exportSchema = false)` — Declara entidades y versión.
14. `abstract class SchoolDatabase : RoomDatabase() {` — Define la base de datos Room.
15. Línea en blanco — Separación visual.
16. `abstract fun professorDao(): ProfessorDao` — Expone el DAO de profesores.
17. `abstract fun courseDao(): CourseDao` — Expone el DAO de cursos.
18. `abstract fun schoolDao(): SchoolDao` — Expone el DAO de relaciones.
19. Línea en blanco — Separación visual.
20. `companion object {` — Bloque estático para singleton.
21. `@Volatile` — Marca la instancia como visible entre hilos.
22. `private var INSTANCE: SchoolDatabase? = null` — Guarda la instancia única.
23. Línea en blanco — Separación visual.
24. `fun getDatabase(context: Context): SchoolDatabase {` — Obtiene o crea la BD.
25. `return INSTANCE ?: synchronized(this) {` — Si no existe, crea con sincronización.
26. `val instance = Room.databaseBuilder(` — Inicia el builder de Room.
27. `context.applicationContext,` — Usa el contexto de aplicación.
28. `SchoolDatabase::class.java,` — Clase de la BD.
29. `"school_database"` — Nombre del archivo de BD.
30. `).fallbackToDestructiveMigration()` — Recrea la BD si hay cambios sin migración.
31. `.build()` — Construye la instancia.
32. `INSTANCE = instance` — Guarda la instancia creada.
33. `instance` — Retorna la instancia.
34. `}` — Cierra el bloque `synchronized`.
35. `}` — Cierra `getDatabase`.
36. `}` — Cierra `companion object`.
37. `}` — Cierra la clase.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/ui/viewmodel/SchoolViewModel.kt`
1. `package com.equipo.proyecto24022026.ui.viewmodel` — Define el paquete del ViewModel.
2. Línea en blanco — Separación visual.
3. `import android.app.Application` — Importa la clase `Application`.
4. `import androidx.lifecycle.AndroidViewModel` — ViewModel con `Application`.
5. `import androidx.lifecycle.viewModelScope` — Scope para coroutines del ViewModel.
6. `import com.equipo.proyecto24022026.data.dao.CourseDao` — Importa DAO de cursos.
7. `import com.equipo.proyecto24022026.data.dao.ProfessorDao` — Importa DAO de profesores.
8. `import com.equipo.proyecto24022026.data.dao.SchoolDao` — Importa DAO de relaciones.
9. `import com.equipo.proyecto24022026.data.database.SchoolDatabase` — Importa la BD.
10. `import com.equipo.proyecto24022026.data.model.Course` — Importa entidad `Course`.
11. `import com.equipo.proyecto24022026.data.model.Professor` — Importa entidad `Professor`.
12. `import com.equipo.proyecto24022026.data.model.ProfessorWithCourses` — Importa relación profesor-cursos.
13. `import kotlinx.coroutines.flow.Flow` — Importa `Flow`.
14. `import kotlinx.coroutines.flow.SharingStarted` — Estrategia de inicio para `stateIn`.
15. `import kotlinx.coroutines.flow.StateFlow` — Importa `StateFlow`.
16. `import kotlinx.coroutines.flow.stateIn` — Convierte `Flow` a `StateFlow`.
17. `import kotlinx.coroutines.launch` — Importa `launch` para coroutines.
18. Línea en blanco — Separación visual.
19. `class SchoolViewModel(application: Application) : AndroidViewModel(application) {` — Declara el ViewModel.
20. Línea en blanco — Separación visual.
21. `private val db = SchoolDatabase.getDatabase(application)` — Obtiene la instancia de BD.
22. `private val professorDao: ProfessorDao = db.professorDao()` — Instancia DAO de profesores.
23. `private val courseDao: CourseDao = db.courseDao()` — Instancia DAO de cursos.
24. `private val schoolDao: SchoolDao = db.schoolDao()` — Instancia DAO de relaciones.
25. Línea en blanco — Separación visual.
26. `val professors: StateFlow<List<Professor>> = professorDao.getAllProfessors()` — Flujo observable de profesores.
27. `.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())` — Convierte a `StateFlow`.
28. Línea en blanco — Separación visual.
29. `val allCourses: StateFlow<List<Course>> = courseDao.getAllCourses()` — Flujo observable de cursos.
30. `.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())` — Convierte a `StateFlow`.
31. Línea en blanco — Separación visual.
32. `val professorsWithCourses: StateFlow<List<ProfessorWithCourses>> = schoolDao.getProfessorsWithCourses()` — Flujo de profesores con cursos.
33. `.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())` — Convierte a `StateFlow`.
34. Línea en blanco — Separación visual.
35. `fun getCoursesByProfessor(professorId: Int): Flow<List<Course>> {` — Consulta cursos por profesor.
36. `return courseDao.getCoursesByProfessor(professorId)` — Devuelve el `Flow` del DAO.
37. `}` — Cierra la función.
38. Línea en blanco — Separación visual.
39. `fun getProfessorWithCourses(professorId: Int): Flow<ProfessorWithCourses?> {` — Consulta relación profesor-cursos.
40. `return schoolDao.getProfessorWithCourses(professorId)` — Devuelve el `Flow` del DAO.
41. `}` — Cierra la función.
42. Línea en blanco — Separación visual.
43. `suspend fun getProfessorById(id: Int): Professor? = professorDao.getProfessorById(id)` — Obtiene profesor por id.
44. Línea en blanco — Separación visual.
45. `suspend fun getCourseById(id: Int): Course? = courseDao.getCourseById(id)` — Obtiene curso por id.
46. Línea en blanco — Separación visual.
47. `fun addProfessor(professor: Professor) = viewModelScope.launch {` — Inserta profesor en coroutine.
48. `professorDao.insertProfessor(professor)` — Llama al DAO para insertar.
49. `}` — Cierra `launch`.
50. Línea en blanco — Separación visual.
51. `fun updateProfessor(professor: Professor) = viewModelScope.launch {` — Actualiza profesor en coroutine.
52. `professorDao.updateProfessor(professor)` — Llama al DAO para actualizar.
53. `}` — Cierra `launch`.
54. Línea en blanco — Separación visual.
55. `fun deleteProfessor(professor: Professor) = viewModelScope.launch {` — Elimina profesor en coroutine.
56. `professorDao.deleteProfessor(professor)` — Llama al DAO para eliminar.
57. `}` — Cierra `launch`.
58. Línea en blanco — Separación visual.
59. `fun addCourse(course: Course) = viewModelScope.launch {` — Inserta curso en coroutine.
60. `courseDao.insertCourse(course)` — Llama al DAO para insertar.
61. `}` — Cierra `launch`.
62. Línea en blanco — Separación visual.
63. `fun updateCourse(course: Course) = viewModelScope.launch {` — Actualiza curso en coroutine.
64. `courseDao.updateCourse(course)` — Llama al DAO para actualizar.
65. `}` — Cierra `launch`.
66. Línea en blanco — Separación visual.
67. `fun deleteCourse(course: Course) = viewModelScope.launch {` — Elimina curso en coroutine.
68. `courseDao.deleteCourse(course)` — Llama al DAO para eliminar.
69. `}` — Cierra `launch`.
70. `}` — Cierra la clase.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/ui/navigation/AppNavigation.kt`
1. `package com.equipo.proyecto24022026.ui.navigation` — Define el paquete de navegación.
2. Línea en blanco — Separación visual.
3. `import androidx.compose.runtime.Composable` — Importa anotación `@Composable`.
4. `import androidx.lifecycle.viewmodel.compose.viewModel` — Obtiene ViewModel en Compose.
5. `import androidx.navigation.NavType` — Tipos para argumentos de navegación.
6. `import androidx.navigation.compose.NavHost` — Contenedor de navegación Compose.
7. `import androidx.navigation.compose.composable` — Destinos composables.
8. `import androidx.navigation.compose.rememberNavController` — Controlador de navegación.
9. `import androidx.navigation.navArgument` — Define argumentos de rutas.
10. `import com.equipo.proyecto24022026.ui.screens.AddEditCourseScreen` — Importa pantalla de curso.
11. `import com.equipo.proyecto24022026.ui.screens.AddEditProfessorScreen` — Importa pantalla de profesor.
12. `import com.equipo.proyecto24022026.ui.screens.CourseListScreen` — Importa lista de cursos.
13. `import com.equipo.proyecto24022026.ui.screens.ProfessorListScreen` — Importa lista de profesores.
14. `import com.equipo.proyecto24022026.ui.viewmodel.SchoolViewModel` — Importa ViewModel principal.
15. Línea en blanco — Separación visual.
16. `sealed class Screen(val route: String) {` — Define rutas de navegación tipadas.
17. `object Professors : Screen("professors")` — Ruta de lista de profesores.
18. `object AddProfessor : Screen("add_professor")` — Ruta de agregar profesor.
19. `object EditProfessor : Screen("edit_professor/{professorId}") {` — Ruta con argumento `professorId`.
20. `fun createRoute(professorId: Int) = "edit_professor/$professorId"` — Construye la ruta dinámica.
21. `}` — Cierra `EditProfessor`.
22. `object CoursesByProfessor : Screen("courses/{professorId}") {` — Ruta de cursos por profesor.
23. `fun createRoute(professorId: Int) = "courses/$professorId"` — Construye la ruta dinámica.
24. `}` — Cierra `CoursesByProfessor`.
25. `object AddCourse : Screen("add_course/{professorId}") {` — Ruta para agregar curso.
26. `fun createRoute(professorId: Int) = "add_course/$professorId"` — Construye la ruta dinámica.
27. `}` — Cierra `AddCourse`.
28. `object EditCourse : Screen("edit_course/{courseId}/{professorId}") {` — Ruta para editar curso.
29. `fun createRoute(courseId: Int, professorId: Int) = "edit_course/$courseId/$professorId"` — Construye la ruta dinámica.
30. `}` — Cierra `EditCourse`.
31. `}` — Cierra la sealed class.
32. Línea en blanco — Separación visual.
33. `@Composable` — Marca función como composable.
34. `fun AppNavigation() {` — Define la navegación principal.
35. `val navController = rememberNavController()` — Crea el controlador de navegación.
36. `val viewModel: SchoolViewModel = viewModel()` — Obtiene el ViewModel en Compose.
37. Línea en blanco — Separación visual.
38. `NavHost(navController = navController, startDestination = Screen.Professors.route) {` — Define el grafo de navegación.
39. `composable(Screen.Professors.route) {` — Destino: lista de profesores.
40. `ProfessorListScreen(` — Llama la pantalla principal.
41. `viewModel = viewModel,` — Inyecta el ViewModel.
42. `onAddProfessor = { navController.navigate(Screen.AddProfessor.route) },` — Navega a agregar profesor.
43. `onEditProfessor = { id -> navController.navigate(Screen.EditProfessor.createRoute(id)) },` — Navega a editar profesor.
44. `onOpenCourses = { id -> navController.navigate(Screen.CoursesByProfessor.createRoute(id)) }` — Navega a cursos del profesor.
45. `)` — Cierra llamada a `ProfessorListScreen`.
46. `}` — Cierra destino `Professors`.
47. `composable(Screen.AddProfessor.route) {` — Destino: agregar profesor.
48. `AddEditProfessorScreen(viewModel = viewModel, navController = navController)` — Llama pantalla de agregar/editar.
49. `}` — Cierra destino `AddProfessor`.
50. `composable(` — Inicia destino con argumentos.
51. `route = Screen.EditProfessor.route,` — Ruta con `professorId`.
52. `arguments = listOf(navArgument("professorId") { type = NavType.IntType })` — Define tipo de argumento.
53. `) { backStackEntry ->` — Bloque con acceso a argumentos.
54. `val professorId = backStackEntry.arguments?.getInt("professorId")` — Lee el id.
55. `AddEditProfessorScreen(` — Llama pantalla de edición.
56. `viewModel = viewModel,` — Inyecta ViewModel.
57. `navController = navController,` — Pasa el navController.
58. `professorId = professorId` — Pasa el id al formulario.
59. `)` — Cierra llamada a pantalla.
60. `}` — Cierra destino `EditProfessor`.
61. `composable(` — Inicia destino de cursos por profesor.
62. `route = Screen.CoursesByProfessor.route,` — Ruta con `professorId`.
63. `arguments = listOf(navArgument("professorId") { type = NavType.IntType })` — Define argumento.
64. `) { backStackEntry ->` — Bloque con argumentos.
65. `val professorId = backStackEntry.arguments?.getInt("professorId") ?: return@composable` — Lee el id o cancela.
66. `CourseListScreen(` — Llama pantalla de cursos.
67. `professorId = professorId,` — Pasa el id.
68. `viewModel = viewModel,` — Inyecta ViewModel.
69. `navController = navController,` — Pasa navController.
70. `onAddCourse = { navController.navigate(Screen.AddCourse.createRoute(professorId)) },` — Navega a agregar curso.
71. `onEditCourse = { courseId -> navController.navigate(Screen.EditCourse.createRoute(courseId, professorId)) }` — Navega a editar curso.
72. `)` — Cierra llamada a pantalla.
73. `}` — Cierra destino `CoursesByProfessor`.
74. `composable(` — Inicia destino agregar curso.
75. `route = Screen.AddCourse.route,` — Ruta con `professorId`.
76. `arguments = listOf(navArgument("professorId") { type = NavType.IntType })` — Define argumento.
77. `) { backStackEntry ->` — Bloque con argumentos.
78. `val professorId = backStackEntry.arguments?.getInt("professorId") ?: return@composable` — Lee el id o cancela.
79. `AddEditCourseScreen(` — Llama pantalla de curso.
80. `viewModel = viewModel,` — Inyecta ViewModel.
81. `navController = navController,` — Pasa navController.
82. `professorId = professorId` — Pasa id del profesor.
83. `)` — Cierra llamada a pantalla.
84. `}` — Cierra destino `AddCourse`.
85. `composable(` — Inicia destino editar curso.
86. `route = Screen.EditCourse.route,` — Ruta con `courseId` y `professorId`.
87. `arguments = listOf(` — Lista de argumentos.
88. `navArgument("courseId") { type = NavType.IntType },` — Argumento `courseId`.
89. `navArgument("professorId") { type = NavType.IntType }` — Argumento `professorId`.
90. `)` — Cierra lista de argumentos.
91. `) { backStackEntry ->` — Bloque con argumentos.
92. `val courseId = backStackEntry.arguments?.getInt("courseId")` — Lee el id del curso.
93. `val professorId = backStackEntry.arguments?.getInt("professorId") ?: return@composable` — Lee el id del profesor.
94. `AddEditCourseScreen(` — Llama pantalla de edición de curso.
95. `viewModel = viewModel,` — Inyecta ViewModel.
96. `navController = navController,` — Pasa navController.
97. `professorId = professorId,` — Pasa id del profesor.
98. `courseId = courseId` — Pasa id del curso.
99. `)` — Cierra llamada a pantalla.
100. `}` — Cierra destino `EditCourse`.
101. `}` — Cierra `NavHost`.
102. `}` — Cierra la función.

**Archivo:** `app/src/main/AndroidManifest.xml`
1. `<?xml version="1.0" encoding="utf-8"?>` — Encabezado XML.
2. `<manifest xmlns:android="http://schemas.android.com/apk/res/android"` — Declara el manifiesto y namespace.
3. `xmlns:tools="http://schemas.android.com/tools">` — Namespace de herramientas.
4. Línea en blanco — Separación visual.
5. `<application` — Abre el bloque de la aplicación.
6. `android:allowBackup="true"` — Permite backups del usuario.
7. `android:dataExtractionRules="@xml/data_extraction_rules"` — Reglas de extracción de datos.
8. `android:fullBackupContent="@xml/backup_rules"` — Reglas de backup completo.
9. `android:icon="@mipmap/ic_launcher"` — Ícono principal.
10. `android:label="@string/app_name"` — Nombre mostrado de la app.
11. `android:roundIcon="@mipmap/ic_launcher_round"` — Ícono redondo.
12. `android:supportsRtl="true"` — Soporte para RTL.
13. `android:theme="@style/Theme.ContactsApp">` — Tema global de la app.
14. `<activity` — Declara la actividad principal.
15. `android:name=".MainActivity"` — Clase de la actividad.
16. `android:exported="true"` — Exportada (requerido con intent-filter).
17. `android:label="@string/app_name"` — Nombre de la actividad.
18. `android:theme="@style/Theme.ContactsApp">` — Tema de la actividad.
19. `<intent-filter>` — Filtro de intención.
20. `<action android:name="android.intent.action.MAIN" />` — Acción principal.
21. `<category android:name="android.intent.category.LAUNCHER" />` — Marca como lanzador.
22. `</intent-filter>` — Cierra filtro.
23. `</activity>` — Cierra actividad.
24. `</application>` — Cierra aplicación.
25. `</manifest>` — Cierra manifiesto.

**Archivo:** `app/src/main/res/values/strings.xml`
1. `<resources>` — Contenedor de recursos de strings.
2. `<string name="app_name">1Proyecto 24-02-2026</string>` — Nombre de la app.
3. `</resources>` — Cierra recursos.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/ui/screens/ProfessorListScreen.kt`
1. `package com.equipo.proyecto24022026.ui.screens` — Define el paquete de pantallas.
2. Línea en blanco — Separación visual.
3. `import androidx.compose.foundation.background` — Importa `background`.
4. `import androidx.compose.foundation.clickable` — Importa `clickable`.
5. `import androidx.compose.foundation.layout.Arrangement` — Importa arreglos de layout.
6. `import androidx.compose.foundation.layout.Box` — Importa contenedor `Box`.
7. `import androidx.compose.foundation.layout.Column` — Importa contenedor `Column`.
8. `import androidx.compose.foundation.layout.PaddingValues` — Importa padding para listas.
9. `import androidx.compose.foundation.layout.Row` — Importa contenedor `Row`.
10. `import androidx.compose.foundation.layout.Spacer` — Importa espaciador.
11. `import androidx.compose.foundation.layout.fillMaxSize` — Importa modifier `fillMaxSize`.
12. `import androidx.compose.foundation.layout.fillMaxWidth` — Importa modifier `fillMaxWidth`.
13. `import androidx.compose.foundation.layout.height` — Importa modifier `height`.
14. `import androidx.compose.foundation.layout.padding` — Importa modifier `padding`.
15. `import androidx.compose.foundation.layout.size` — Importa modifier `size`.
16. `import androidx.compose.foundation.lazy.LazyColumn` — Importa lista perezosa.
17. `import androidx.compose.foundation.lazy.items` — Importa items para `LazyColumn`.
18. `import androidx.compose.foundation.shape.RoundedCornerShape` — Importa bordes redondeados.
19. `import androidx.compose.material.icons.Icons` — Importa íconos base.
20. `import androidx.compose.material.icons.filled.Add` — Importa ícono de agregar.
21. `import androidx.compose.material.icons.filled.Delete` — Importa ícono de eliminar.
22. `import androidx.compose.material.icons.filled.Edit` — Importa ícono de editar.
23. `import androidx.compose.material.icons.filled.MenuBook` — Importa ícono de cursos.
24. `import androidx.compose.material3.AlertDialog` — Importa diálogo.
25. `import androidx.compose.material3.Card` — Importa tarjeta.
26. `import androidx.compose.material3.CardDefaults` — Importa defaults de tarjeta.
27. `import androidx.compose.material3.ExperimentalMaterial3Api` — Marca APIs experimentales.
28. `import androidx.compose.material3.FloatingActionButton` — Importa FAB.
29. `import androidx.compose.material3.Icon` — Importa componente de icono.
30. `import androidx.compose.material3.IconButton` — Importa botón de icono.
31. `import androidx.compose.material3.MaterialTheme` — Importa tema Material.
32. `import androidx.compose.material3.Scaffold` — Importa layout Scaffold.
33. `import androidx.compose.material3.Text` — Importa texto.
34. `import androidx.compose.material3.TextButton` — Importa botón de texto.
35. `import androidx.compose.material3.TopAppBar` — Importa barra superior.
36. `import androidx.compose.material3.TopAppBarDefaults` — Importa defaults de TopAppBar.
37. `import androidx.compose.runtime.Composable` — Importa `@Composable`.
38. `import androidx.compose.runtime.collectAsState` — Convierte `Flow` a estado.
39. `import androidx.compose.runtime.getValue` — Soporte para `by`.
40. `import androidx.compose.runtime.mutableStateOf` — Estado local mutable.
41. `import androidx.compose.runtime.remember` — Recuerda estado.
42. `import androidx.compose.runtime.setValue` — Soporte para asignación con `by`.
43. `import androidx.compose.ui.Alignment` — Alineaciones.
44. `import androidx.compose.ui.Modifier` — Modificadores de Compose.
45. `import androidx.compose.ui.text.font.FontWeight` — Peso de fuente.
46. `import androidx.compose.ui.unit.dp` — Unidades en dp.
47. `import com.equipo.proyecto24022026.data.model.Professor` — Importa modelo `Professor`.
48. `import com.equipo.proyecto24022026.ui.viewmodel.SchoolViewModel` — Importa ViewModel.
49. Línea en blanco — Separación visual.
50. `@OptIn(ExperimentalMaterial3Api::class)` — Habilita APIs experimentales.
51. `@Composable` — Marca función composable.
52. `fun ProfessorListScreen(` — Pantalla principal de profesores.
53. `viewModel: SchoolViewModel,` — ViewModel inyectado.
54. `onAddProfessor: () -> Unit,` — Callback para agregar.
55. `onEditProfessor: (Int) -> Unit,` — Callback para editar.
56. `onOpenCourses: (Int) -> Unit` — Callback para ver cursos.
57. `) {` — Abre el cuerpo.
58. `val professorsWithCourses by viewModel.professorsWithCourses.collectAsState()` — Observa profesores con cursos.
59. `val totalCourses = professorsWithCourses.sumOf { it.courses.size }` — Calcula cursos totales.
60. Línea en blanco — Separación visual.
61. `Scaffold(` — Estructura base de la pantalla.
62. `topBar = {` — Define la barra superior.
63. `TopAppBar(` — Crea la TopAppBar.
64. `title = { Text("Profesores") },` — Título de la pantalla.
65. `colors = TopAppBarDefaults.topAppBarColors(` — Colores de la barra.
66. `containerColor = MaterialTheme.colorScheme.primaryContainer` — Color de fondo.
67. `)` — Cierra colores.
68. `)` — Cierra TopAppBar.
69. `},` — Cierra bloque `topBar`.
70. `floatingActionButton = {` — Define el FAB.
71. `FloatingActionButton(onClick = onAddProfessor) {` — Botón para agregar.
72. `Icon(Icons.Default.Add, contentDescription = "Agregar profesor")` — Ícono del FAB.
73. `}` — Cierra FAB.
74. `}` — Cierra bloque FAB.
75. `) { padding ->` — Contenido con padding de Scaffold.
76. `Column(` — Contenedor vertical.
77. `modifier = Modifier` — Inicio de modificadores.
78. `.padding(padding)` — Aplica padding del Scaffold.
79. `.fillMaxSize()` — Ocupa toda la pantalla.
80. `)` — Cierra modificadores.
81. `{` — Abre contenido.
82. `SummaryHeader(` — Encabezado resumen.
83. `title = "Gestion academica",` — Título del resumen.
84. `subtitle = "${professorsWithCourses.size} profesores | $totalCourses cursos registrados"` — Subtítulo dinámico.
85. `)` — Cierra `SummaryHeader`.
86. Línea en blanco — Separación visual.
87. `if (professorsWithCourses.isEmpty()) {` — Si no hay profesores.
88. `EmptyState("Aun no hay profesores.\nAgrega el primero para empezar.")` — Muestra estado vacío.
89. `} else {` — Si hay datos.
90. `LazyColumn(` — Lista de profesores.
91. `contentPadding = PaddingValues(16.dp),` — Padding de la lista.
92. `verticalArrangement = Arrangement.spacedBy(12.dp)` — Espaciado entre items.
93. `) {` — Abre lista.
94. `items(professorsWithCourses, key = { it.professor.id }) { item ->` — Itera con key.
95. `ProfessorCard(` — Tarjeta de profesor.
96. `professor = item.professor,` — Pasa el profesor.
97. `courseCount = item.courses.size,` — Cantidad de cursos.
98. `onOpenCourses = { onOpenCourses(item.professor.id) },` — Abre cursos.
99. `onEdit = { onEditProfessor(item.professor.id) },` — Edita profesor.
100. `onDelete = { viewModel.deleteProfessor(item.professor) }` — Elimina profesor.
101. `)` — Cierra tarjeta.
102. `}` — Cierra `items`.
103. `}` — Cierra `LazyColumn`.
104. `}` — Cierra `else`.
105. `}` — Cierra `Column`.
106. `}` — Cierra `Scaffold`.
107. `}` — Cierra `ProfessorListScreen`.
108. Línea en blanco — Separación visual.
109. `@Composable` — Marca función composable.
110. `private fun SummaryHeader(title: String, subtitle: String) {` — Encabezado resumen.
111. `Card(` — Tarjeta de resumen.
112. `modifier = Modifier` — Modificadores.
113. `.fillMaxWidth()` — Ocupa ancho.
114. `.padding(horizontal = 16.dp, vertical = 12.dp),` — Padding exterior.
115. `colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),` — Colores de tarjeta.
116. `shape = RoundedCornerShape(20.dp)` — Bordes redondeados.
117. `) {` — Abre contenido de tarjeta.
118. `Column(modifier = Modifier.padding(16.dp)) {` — Columna con padding interno.
119. `Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)` — Título.
120. `Spacer(modifier = Modifier.height(4.dp))` — Separador.
121. `Text(subtitle, style = MaterialTheme.typography.bodyMedium)` — Subtítulo.
122. `}` — Cierra `Column`.
123. `}` — Cierra `Card`.
124. `}` — Cierra `SummaryHeader`.
125. Línea en blanco — Separación visual.
126. `@Composable` — Marca función composable.
127. `private fun ProfessorCard(` — Tarjeta por profesor.
128. `professor: Professor,` — Profesor a mostrar.
129. `courseCount: Int,` — Cantidad de cursos.
130. `onOpenCourses: () -> Unit,` — Callback ver cursos.
131. `onEdit: () -> Unit,` — Callback editar.
132. `onDelete: () -> Unit` — Callback eliminar.
133. `) {` — Abre cuerpo.
134. `var showDeleteDialog by remember { mutableStateOf(false) }` — Estado para diálogo.
135. Línea en blanco — Separación visual.
136. `if (showDeleteDialog) {` — Si se solicita borrar.
137. `ConfirmDeleteDialog(` — Muestra diálogo.
138. `title = "Eliminar profesor",` — Título.
139. `message = "Se eliminara al profesor y sus cursos asociados.",` — Mensaje.
140. `onConfirm = {` — Acción de confirmar.
141. `onDelete()` — Ejecuta borrado.
142. `showDeleteDialog = false` — Cierra diálogo.
143. `},` — Cierra `onConfirm`.
144. `onDismiss = { showDeleteDialog = false }` — Cancela diálogo.
145. `)` — Cierra diálogo.
146. `}` — Cierra `if`.
147. Línea en blanco — Separación visual.
148. `Card(` — Tarjeta del profesor.
149. `modifier = Modifier` — Modificadores.
150. `.fillMaxWidth()` — Ocupa ancho.
151. `.clickable(onClick = onOpenCourses),` — Abre cursos al tocar.
152. `shape = RoundedCornerShape(18.dp),` — Bordes redondeados.
153. `colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))` — Color de fondo.
154. `) {` — Abre contenido.
155. `Column(modifier = Modifier.padding(16.dp)) {` — Columna interna.
156. `Row(verticalAlignment = Alignment.CenterVertically) {` — Fila para avatar y datos.
157. `Box(` — Contenedor del avatar.
158. `modifier = Modifier` — Modificadores.
159. `.size(44.dp)` — Tamaño del avatar.
160. `.background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(12.dp)),` — Fondo y borde.
161. `contentAlignment = Alignment.Center` — Centra el texto.
162. `) {` — Abre `Box`.
163. `Text(` — Inicial del nombre.
164. `text = professor.name.trim().take(1).uppercase(),` — Primera letra en mayúscula.
165. `style = MaterialTheme.typography.titleMedium,` — Estilo.
166. `fontWeight = FontWeight.Bold` — Negrita.
167. `)` — Cierra `Text`.
168. `}` — Cierra `Box`.
169. `Spacer(modifier = Modifier.size(12.dp))` — Espacio entre avatar y texto.
170. `Column(modifier = Modifier.weight(1f)) {` — Columna con datos.
171. `Text(professor.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)` — Nombre.
172. `Text(professor.specialty, style = MaterialTheme.typography.bodyMedium)` — Especialidad.
173. `if (professor.email.isNotBlank()) {` — Si hay email.
174. `Text(professor.email, style = MaterialTheme.typography.bodySmall)` — Muestra email.
175. `}` — Cierra `if`.
176. `}` — Cierra columna de datos.
177. `CourseCountChip(courseCount)` — Chip con cantidad de cursos.
178. `}` — Cierra `Row`.
179. Línea en blanco — Separación visual.
180. `Spacer(modifier = Modifier.height(12.dp))` — Separador vertical.
181. `Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {` — Fila de acciones.
182. `IconButton(onClick = onOpenCourses) {` — Botón ver cursos.
183. `Icon(Icons.Default.MenuBook, contentDescription = "Ver cursos")` — Ícono cursos.
184. `}` — Cierra botón.
185. `IconButton(onClick = onEdit) {` — Botón editar.
186. `Icon(Icons.Default.Edit, contentDescription = "Editar profesor")` — Ícono editar.
187. `}` — Cierra botón.
188. `IconButton(onClick = { showDeleteDialog = true }) {` — Botón eliminar.
189. `Icon(Icons.Default.Delete, contentDescription = "Eliminar profesor")` — Ícono eliminar.
190. `}` — Cierra botón.
191. `}` — Cierra fila de acciones.
192. `}` — Cierra `Column`.
193. `}` — Cierra `Card`.
194. `}` — Cierra `ProfessorCard`.
195. Línea en blanco — Separación visual.
196. `@Composable` — Marca función composable.
197. `private fun CourseCountChip(count: Int) {` — Chip con cantidad de cursos.
198. `Box(` — Contenedor del chip.
199. `modifier = Modifier` — Modificadores.
200. `.background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))` — Fondo del chip.
201. `.padding(horizontal = 10.dp, vertical = 6.dp)` — Padding interno.
202. `) {` — Abre `Box`.
203. `Text(` — Texto del chip.
204. `text = "$count cursos",` — Muestra cantidad.
205. `color = MaterialTheme.colorScheme.onPrimary,` — Color del texto.
206. `style = MaterialTheme.typography.labelMedium` — Estilo.
207. `)` — Cierra `Text`.
208. `}` — Cierra `Box`.
209. `}` — Cierra `CourseCountChip`.
210. Línea en blanco — Separación visual.
211. `@Composable` — Marca función composable.
212. `fun ConfirmDeleteDialog(` — Diálogo de confirmación.
213. `title: String,` — Título del diálogo.
214. `message: String,` — Mensaje del diálogo.
215. `onConfirm: () -> Unit,` — Acción de confirmar.
216. `onDismiss: () -> Unit` — Acción de cancelar.
217. `) {` — Abre cuerpo.
218. `AlertDialog(` — Componente de diálogo.
219. `onDismissRequest = onDismiss,` — Maneja cierre.
220. `title = { Text(title) },` — Título.
221. `text = { Text(message) },` — Mensaje.
222. `confirmButton = { TextButton(onClick = onConfirm) { Text("Eliminar") } },` — Botón confirmar.
223. `dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }` — Botón cancelar.
224. `)` — Cierra `AlertDialog`.
225. `}` — Cierra `ConfirmDeleteDialog`.
226. Línea en blanco — Separación visual.
227. `@Composable` — Marca función composable.
228. `fun EmptyState(message: String) {` — Componente de estado vacío.
229. `Box(` — Contenedor.
230. `modifier = Modifier.fillMaxSize(),` — Ocupa toda la pantalla.
231. `contentAlignment = Alignment.Center` — Centra contenido.
232. `) {` — Abre `Box`.
233. `Text(` — Texto del estado vacío.
234. `text = message,` — Mensaje dinámico.
235. `style = MaterialTheme.typography.bodyLarge,` — Estilo.
236. `modifier = Modifier.padding(24.dp)` — Padding.
237. `)` — Cierra `Text`.
238. `}` — Cierra `Box`.
239. `}` — Cierra `EmptyState`.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/ui/screens/AddEditProfessorScreen.kt`
1. `package com.equipo.proyecto24022026.ui.screens` — Define el paquete de pantallas.
2. Línea en blanco — Separación visual.
3. `import android.widget.Toast` — Importa `Toast` para mensajes.
4. `import androidx.compose.foundation.layout.Arrangement` — Importa arreglos de layout.
5. `import androidx.compose.foundation.layout.Column` — Importa `Column`.
6. `import androidx.compose.foundation.layout.Spacer` — Importa `Spacer`.
7. `import androidx.compose.foundation.layout.fillMaxWidth` — Importa `fillMaxWidth`.
8. `import androidx.compose.foundation.layout.height` — Importa `height`.
9. `import androidx.compose.foundation.layout.padding` — Importa `padding`.
10. `import androidx.compose.foundation.rememberScrollState` — Importa estado de scroll.
11. `import androidx.compose.foundation.verticalScroll` — Importa scroll vertical.
12. `import androidx.compose.material.icons.Icons` — Importa íconos base.
13. `import androidx.compose.material.icons.filled.ArrowBack` — Ícono volver.
14. `import androidx.compose.material.icons.filled.Email` — Ícono email.
15. `import androidx.compose.material.icons.filled.Person` — Ícono persona.
16. `import androidx.compose.material.icons.filled.School` — Ícono escuela.
17. `import androidx.compose.material3.Button` — Importa botón.
18. `import androidx.compose.material3.ExperimentalMaterial3Api` — APIs experimentales.
19. `import androidx.compose.material3.Icon` — Importa icono.
20. `import androidx.compose.material3.IconButton` — Importa botón de icono.
21. `import androidx.compose.material3.MaterialTheme` — Importa tema.
22. `import androidx.compose.material3.OutlinedTextField` — Importa input.
23. `import androidx.compose.material3.Scaffold` — Importa Scaffold.
24. `import androidx.compose.material3.Text` — Importa texto.
25. `import androidx.compose.material3.TopAppBar` — Importa TopAppBar.
26. `import androidx.compose.material3.TopAppBarDefaults` — Defaults de TopAppBar.
27. `import androidx.compose.runtime.Composable` — Importa `@Composable`.
28. `import androidx.compose.runtime.LaunchedEffect` — Efecto para cargar datos.
29. `import androidx.compose.runtime.getValue` — Soporte `by`.
30. `import androidx.compose.runtime.mutableStateOf` — Estado local mutable.
31. `import androidx.compose.runtime.remember` — Recuerda estado.
32. `import androidx.compose.runtime.setValue` — Soporte asignación `by`.
33. `import androidx.compose.ui.Modifier` — Modificadores Compose.
34. `import androidx.compose.ui.platform.LocalContext` — Contexto local.
35. `import androidx.compose.ui.unit.dp` — Unidades dp.
36. `import androidx.navigation.NavController` — Controlador de navegación.
37. `import com.equipo.proyecto24022026.data.model.Professor` — Modelo `Professor`.
38. `import com.equipo.proyecto24022026.ui.viewmodel.SchoolViewModel` — ViewModel.
39. Línea en blanco — Separación visual.
40. `@OptIn(ExperimentalMaterial3Api::class)` — Habilita APIs experimentales.
41. `@Composable` — Marca función composable.
42. `fun AddEditProfessorScreen(` — Pantalla agregar/editar profesor.
43. `viewModel: SchoolViewModel,` — ViewModel inyectado.
44. `navController: NavController,` — Controlador de navegación.
45. `professorId: Int? = null` — Id opcional para edición.
46. `) {` — Abre cuerpo.
47. `val isEditing = professorId != null` — Define modo edición.
48. `val context = LocalContext.current` — Obtiene el contexto.
49. Línea en blanco — Separación visual.
50. `var name by remember { mutableStateOf("") }` — Estado del nombre.
51. `var specialty by remember { mutableStateOf("") }` — Estado de especialidad.
52. `var email by remember { mutableStateOf("") }` — Estado del email.
53. Línea en blanco — Separación visual.
54. `LaunchedEffect(professorId) {` — Carga datos cuando cambia el id.
55. `if (isEditing) {` — Si es edición.
56. `viewModel.getProfessorById(professorId!!)?.let { professor ->` — Busca profesor.
57. `name = professor.name` — Rellena nombre.
58. `specialty = professor.specialty` — Rellena especialidad.
59. `email = professor.email` — Rellena email.
60. `}` — Cierra `let`.
61. `}` — Cierra `if`.
62. `}` — Cierra `LaunchedEffect`.
63. Línea en blanco — Separación visual.
64. `Scaffold(` — Estructura base.
65. `topBar = {` — Barra superior.
66. `TopAppBar(` — Componente TopAppBar.
67. `title = { Text(if (isEditing) "Editar profesor" else "Agregar profesor") },` — Título dinámico.
68. `navigationIcon = {` — Icono de navegación.
69. `IconButton(onClick = { navController.popBackStack() }) {` — Botón volver.
70. `Icon(Icons.Default.ArrowBack, contentDescription = "Volver")` — Ícono volver.
71. `}` — Cierra botón.
72. `},` — Cierra `navigationIcon`.
73. `colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)` — Color de barra.
74. `)` — Cierra TopAppBar.
75. `}` — Cierra `topBar`.
76. `) { padding ->` — Contenido con padding.
77. `Column(` — Columna principal.
78. `modifier = Modifier` — Modificadores.
79. `.padding(padding)` — Aplica padding del Scaffold.
80. `.padding(16.dp)` — Padding interno.
81. `.verticalScroll(rememberScrollState()),` — Habilita scroll.
82. `verticalArrangement = Arrangement.spacedBy(10.dp)` — Espaciado entre campos.
83. `) {` — Abre contenido.
84. `OutlinedTextField(` — Input nombre.
85. `value = name,` — Valor actual.
86. `onValueChange = { name = it },` — Actualiza estado.
87. `label = { Text("Nombre completo") },` — Etiqueta.
88. `leadingIcon = { Icon(Icons.Default.Person, null) },` — Ícono.
89. `modifier = Modifier.fillMaxWidth()` — Ocupa ancho.
90. `)` — Cierra input.
91. `OutlinedTextField(` — Input especialidad.
92. `value = specialty,` — Valor actual.
93. `onValueChange = { specialty = it },` — Actualiza estado.
94. `label = { Text("Especialidad") },` — Etiqueta.
95. `leadingIcon = { Icon(Icons.Default.School, null) },` — Ícono.
96. `modifier = Modifier.fillMaxWidth()` — Ocupa ancho.
97. `)` — Cierra input.
98. `OutlinedTextField(` — Input email.
99. `value = email,` — Valor actual.
100. `onValueChange = { email = it },` — Actualiza estado.
101. `label = { Text("Email (opcional)") },` — Etiqueta.
102. `leadingIcon = { Icon(Icons.Default.Email, null) },` — Ícono.
103. `modifier = Modifier.fillMaxWidth()` — Ocupa ancho.
104. `)` — Cierra input.
105. Línea en blanco — Separación visual.
106. `Spacer(modifier = Modifier.height(8.dp))` — Separador.
107. Línea en blanco — Separación visual.
108. `Button(` — Botón guardar.
109. `onClick = {` — Acción al guardar.
110. `if (name.isBlank() || specialty.isBlank()) {` — Validación básica.
111. `Toast.makeText(context, "Nombre y especialidad son obligatorios", Toast.LENGTH_SHORT).show()` — Mensaje de error.
112. `return@Button` — Sale del click.
113. `}` — Cierra `if`.
114. Línea en blanco — Separación visual.
115. `val professor = Professor(` — Crea objeto profesor.
116. `id = professorId ?: 0,` — Usa id si edita o 0 si nuevo.
117. `name = name.trim(),` — Guarda nombre limpio.
118. `specialty = specialty.trim(),` — Guarda especialidad limpia.
119. `email = email.trim()` — Guarda email limpio.
120. `)` — Cierra objeto.
121. Línea en blanco — Separación visual.
122. `if (isEditing) viewModel.updateProfessor(professor) else viewModel.addProfessor(professor)` — Actualiza o inserta.
123. `navController.popBackStack()` — Vuelve a la pantalla anterior.
124. `},` — Cierra `onClick`.
125. `modifier = Modifier.fillMaxWidth()` — Botón a ancho completo.
126. `) {` — Abre contenido del botón.
127. `Text("Guardar profesor")` — Texto del botón.
128. `}` — Cierra botón.
129. `}` — Cierra `Column`.
130. `}` — Cierra `Scaffold`.
131. `}` — Cierra `AddEditProfessorScreen`.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/ui/screens/CourseListScreen.kt`
1. `package com.equipo.proyecto24022026.ui.screens` — Define el paquete de pantallas.
2. Línea en blanco — Separación visual.
3. `import androidx.compose.foundation.layout.Arrangement` — Importa arreglos de layout.
4. `import androidx.compose.foundation.layout.Column` — Importa `Column`.
5. `import androidx.compose.foundation.layout.PaddingValues` — Importa `PaddingValues`.
6. `import androidx.compose.foundation.layout.Row` — Importa `Row`.
7. `import androidx.compose.foundation.layout.Spacer` — Importa `Spacer`.
8. `import androidx.compose.foundation.layout.fillMaxSize` — Importa `fillMaxSize`.
9. `import androidx.compose.foundation.layout.fillMaxWidth` — Importa `fillMaxWidth`.
10. `import androidx.compose.foundation.layout.height` — Importa `height`.
11. `import androidx.compose.foundation.layout.padding` — Importa `padding`.
12. `import androidx.compose.foundation.lazy.LazyColumn` — Importa lista perezosa.
13. `import androidx.compose.foundation.lazy.items` — Importa items para lista.
14. `import androidx.compose.foundation.shape.RoundedCornerShape` — Bordes redondeados.
15. `import androidx.compose.material.icons.Icons` — Íconos base.
16. `import androidx.compose.material.icons.filled.Add` — Ícono agregar.
17. `import androidx.compose.material.icons.filled.ArrowBack` — Ícono volver.
18. `import androidx.compose.material.icons.filled.Delete` — Ícono eliminar.
19. `import androidx.compose.material.icons.filled.Edit` — Ícono editar.
20. `import androidx.compose.material.icons.filled.Schedule` — Ícono horario.
21. `import androidx.compose.material3.Card` — Tarjeta.
22. `import androidx.compose.material3.CardDefaults` — Defaults de tarjeta.
23. `import androidx.compose.material3.ExperimentalMaterial3Api` — APIs experimentales.
24. `import androidx.compose.material3.FloatingActionButton` — FAB.
25. `import androidx.compose.material3.Icon` — Ícono.
26. `import androidx.compose.material3.IconButton` — Botón de ícono.
27. `import androidx.compose.material3.MaterialTheme` — Tema.
28. `import androidx.compose.material3.Scaffold` — Scaffold.
29. `import androidx.compose.material3.Text` — Texto.
30. `import androidx.compose.material3.TopAppBar` — TopAppBar.
31. `import androidx.compose.material3.TopAppBarDefaults` — Defaults de TopAppBar.
32. `import androidx.compose.runtime.Composable` — `@Composable`.
33. `import androidx.compose.runtime.collectAsState` — Flow a estado.
34. `import androidx.compose.runtime.getValue` — Soporte `by`.
35. `import androidx.compose.runtime.mutableStateOf` — Estado local mutable.
36. `import androidx.compose.runtime.remember` — Recuerda estado.
37. `import androidx.compose.runtime.setValue` — Soporte asignación.
38. `import androidx.compose.ui.Modifier` — Modificadores.
39. `import androidx.compose.ui.text.font.FontWeight` — Peso de fuente.
40. `import androidx.compose.ui.unit.dp` — Unidades dp.
41. `import androidx.navigation.NavController` — NavController.
42. `import com.equipo.proyecto24022026.data.model.Course` — Modelo `Course`.
43. `import com.equipo.proyecto24022026.ui.viewmodel.SchoolViewModel` — ViewModel.
44. Línea en blanco — Separación visual.
45. `@OptIn(ExperimentalMaterial3Api::class)` — Habilita APIs experimentales.
46. `@Composable` — Marca función composable.
47. `fun CourseListScreen(` — Pantalla de cursos.
48. `professorId: Int,` — Id del profesor.
49. `viewModel: SchoolViewModel,` — ViewModel inyectado.
50. `navController: NavController,` — NavController.
51. `onAddCourse: () -> Unit,` — Callback para agregar curso.
52. `onEditCourse: (Int) -> Unit` — Callback para editar curso.
53. `) {` — Abre cuerpo.
54. `val professorWithCourses by viewModel.getProfessorWithCourses(professorId).collectAsState(initial = null)` — Observa relación.
55. `val courses = professorWithCourses?.courses ?: emptyList()` — Lista de cursos o vacía.
56. Línea en blanco — Separación visual.
57. `Scaffold(` — Estructura base.
58. `topBar = {` — Barra superior.
59. `TopAppBar(` — TopAppBar.
60. `title = { Text(professorWithCourses?.professor?.name ?: "Cursos") },` — Título dinámico.
61. `navigationIcon = {` — Icono de navegación.
62. `IconButton(onClick = { navController.popBackStack() }) {` — Botón volver.
63. `Icon(Icons.Default.ArrowBack, contentDescription = "Volver")` — Ícono volver.
64. `}` — Cierra botón.
65. `},` — Cierra `navigationIcon`.
66. `colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)` — Color de barra.
67. `)` — Cierra TopAppBar.
68. `},` — Cierra `topBar`.
69. `floatingActionButton = {` — FAB.
70. `FloatingActionButton(onClick = onAddCourse) {` — Botón agregar curso.
71. `Icon(Icons.Default.Add, contentDescription = "Agregar curso")` — Ícono.
72. `}` — Cierra FAB.
73. `}` — Cierra bloque FAB.
74. `) { padding ->` — Contenido con padding.
75. `Column(` — Columna principal.
76. `modifier = Modifier` — Modificadores.
77. `.padding(padding)` — Padding del Scaffold.
78. `.fillMaxSize()` — Ocupa pantalla.
79. `)` — Cierra modificadores.
80. `{` — Abre contenido.
81. `Card(` — Tarjeta de resumen.
82. `modifier = Modifier` — Modificadores.
83. `.fillMaxWidth()` — Ancho completo.
84. `.padding(horizontal = 16.dp, vertical = 12.dp),` — Padding externo.
85. `shape = RoundedCornerShape(20.dp),` — Bordes redondeados.
86. `colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)` — Color de tarjeta.
87. `) {` — Abre tarjeta.
88. `Column(modifier = Modifier.padding(16.dp)) {` — Columna interna.
89. `Text("Cursos del profesor", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)` — Título.
90. `Spacer(modifier = Modifier.height(4.dp))` — Separador.
91. `Text("Total: ${courses.size}")` — Total de cursos.
92. `professorWithCourses?.professor?.specialty?.let { Text("Especialidad: $it") }` — Muestra especialidad si existe.
93. `}` — Cierra columna.
94. `}` — Cierra tarjeta.
95. Línea en blanco — Separación visual.
96. `if (courses.isEmpty()) {` — Si no hay cursos.
97. `EmptyState("Este profesor aun no tiene cursos registrados.")` — Estado vacío.
98. `} else {` — Si hay cursos.
99. `LazyColumn(` — Lista de cursos.
100. `contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),` — Padding de la lista.
101. `verticalArrangement = Arrangement.spacedBy(10.dp)` — Espaciado.
102. `) {` — Abre lista.
103. `items(courses, key = { it.id }) { course ->` — Itera cursos.
104. `CourseCard(` — Tarjeta de curso.
105. `course = course,` — Pasa curso.
106. `onEdit = { onEditCourse(course.id) },` — Edita curso.
107. `onDelete = { viewModel.deleteCourse(course) }` — Elimina curso.
108. `)` — Cierra tarjeta.
109. `}` — Cierra `items`.
110. `}` — Cierra `LazyColumn`.
111. `}` — Cierra `else`.
112. `}` — Cierra `Column`.
113. `}` — Cierra `Scaffold`.
114. `}` — Cierra `CourseListScreen`.
115. Línea en blanco — Separación visual.
116. `@Composable` — Marca función composable.
117. `private fun CourseCard(` — Tarjeta por curso.
118. `course: Course,` — Curso a mostrar.
119. `onEdit: () -> Unit,` — Callback editar.
120. `onDelete: () -> Unit` — Callback eliminar.
121. `) {` — Abre cuerpo.
122. `var showDeleteDialog by remember { mutableStateOf(false) }` — Estado del diálogo.
123. Línea en blanco — Separación visual.
124. `if (showDeleteDialog) {` — Si se pidió borrar.
125. `ConfirmDeleteDialog(` — Muestra diálogo.
126. `title = "Eliminar curso",` — Título.
127. `message = "Se eliminara el curso ${course.title}.",` — Mensaje.
128. `onConfirm = {` — Confirmación.
129. `onDelete()` — Ejecuta borrado.
130. `showDeleteDialog = false` — Cierra diálogo.
131. `},` — Cierra confirmación.
132. `onDismiss = { showDeleteDialog = false }` — Cancela diálogo.
133. `)` — Cierra diálogo.
134. `}` — Cierra `if`.
135. Línea en blanco — Separación visual.
136. `Card(` — Tarjeta del curso.
137. `modifier = Modifier.fillMaxWidth(),` — Ancho completo.
138. `shape = RoundedCornerShape(16.dp),` — Bordes redondeados.
139. `colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))` — Color fondo.
140. `) {` — Abre tarjeta.
141. `Column(modifier = Modifier.padding(14.dp)) {` — Columna interna.
142. `Text(course.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)` — Título.
143. `Spacer(modifier = Modifier.height(6.dp))` — Separador.
144. `Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {` — Fila para horario.
145. `Icon(Icons.Default.Schedule, contentDescription = null)` — Ícono horario.
146. `Text(course.schedule, style = MaterialTheme.typography.bodyMedium)` — Horario.
147. `}` — Cierra fila.
148. `Spacer(modifier = Modifier.height(4.dp))` — Separador.
149. `Text("Creditos: ${course.credits}", style = MaterialTheme.typography.bodySmall)` — Créditos.
150. `Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {` — Fila de acciones.
151. `IconButton(onClick = onEdit) {` — Botón editar.
152. `Icon(Icons.Default.Edit, contentDescription = "Editar curso")` — Ícono editar.
153. `}` — Cierra botón.
154. `IconButton(onClick = { showDeleteDialog = true }) {` — Botón eliminar.
155. `Icon(Icons.Default.Delete, contentDescription = "Eliminar curso")` — Ícono eliminar.
156. `}` — Cierra botón.
157. `}` — Cierra fila.
158. `}` — Cierra columna.
159. `}` — Cierra tarjeta.
160. `}` — Cierra `CourseCard`.

**Archivo:** `app/src/main/java/com/equipo/proyecto24022026/ui/screens/AddEditCourseScreen.kt`
1. `package com.equipo.proyecto24022026.ui.screens` — Define el paquete de pantallas.
2. Línea en blanco — Separación visual.
3. `import android.widget.Toast` — Importa `Toast`.
4. `import androidx.compose.foundation.layout.Arrangement` — Importa arreglos de layout.
5. `import androidx.compose.foundation.layout.Column` — Importa `Column`.
6. `import androidx.compose.foundation.layout.Spacer` — Importa `Spacer`.
7. `import androidx.compose.foundation.layout.fillMaxWidth` — Importa `fillMaxWidth`.
8. `import androidx.compose.foundation.layout.height` — Importa `height`.
9. `import androidx.compose.foundation.layout.padding` — Importa `padding`.
10. `import androidx.compose.foundation.rememberScrollState` — Estado de scroll.
11. `import androidx.compose.foundation.verticalScroll` — Scroll vertical.
12. `import androidx.compose.material.icons.Icons` — Íconos base.
13. `import androidx.compose.material.icons.filled.ArrowBack` — Ícono volver.
14. `import androidx.compose.material.icons.filled.Book` — Ícono curso.
15. `import androidx.compose.material.icons.filled.Numbers` — Ícono números.
16. `import androidx.compose.material.icons.filled.Schedule` — Ícono horario.
17. `import androidx.compose.material.icons.filled.School` — Ícono profesor.
18. `import androidx.compose.material3.Button` — Botón.
19. `import androidx.compose.material3.ExperimentalMaterial3Api` — APIs experimentales.
20. `import androidx.compose.material3.Icon` — Ícono.
21. `import androidx.compose.material3.IconButton` — Botón ícono.
22. `import androidx.compose.material3.MaterialTheme` — Tema.
23. `import androidx.compose.material3.OutlinedTextField` — Input.
24. `import androidx.compose.material3.Scaffold` — Scaffold.
25. `import androidx.compose.material3.Text` — Texto.
26. `import androidx.compose.material3.TopAppBar` — TopAppBar.
27. `import androidx.compose.material3.TopAppBarDefaults` — Defaults de TopAppBar.
28. `import androidx.compose.runtime.Composable` — `@Composable`.
29. `import androidx.compose.runtime.LaunchedEffect` — Efecto.
30. `import androidx.compose.runtime.getValue` — Soporte `by`.
31. `import androidx.compose.runtime.mutableStateOf` — Estado local mutable.
32. `import androidx.compose.runtime.remember` — Recuerda estado.
33. `import androidx.compose.runtime.setValue` — Soporte asignación.
34. `import androidx.compose.ui.Modifier` — Modificadores.
35. `import androidx.compose.ui.platform.LocalContext` — Contexto local.
36. `import androidx.compose.ui.unit.dp` — Unidades dp.
37. `import androidx.navigation.NavController` — NavController.
38. `import com.equipo.proyecto24022026.data.model.Course` — Modelo `Course`.
39. `import com.equipo.proyecto24022026.ui.viewmodel.SchoolViewModel` — ViewModel.
40. Línea en blanco — Separación visual.
41. `@OptIn(ExperimentalMaterial3Api::class)` — Habilita APIs experimentales.
42. `@Composable` — Marca función composable.
43. `fun AddEditCourseScreen(` — Pantalla agregar/editar curso.
44. `viewModel: SchoolViewModel,` — ViewModel inyectado.
45. `navController: NavController,` — NavController.
46. `professorId: Int,` — Id del profesor.
47. `courseId: Int? = null` — Id opcional para editar.
48. `) {` — Abre cuerpo.
49. `val isEditing = courseId != null` — Define modo edición.
50. `val context = LocalContext.current` — Obtiene contexto.
51. Línea en blanco — Separación visual.
52. `var title by remember { mutableStateOf("") }` — Estado del título.
53. `var schedule by remember { mutableStateOf("") }` — Estado del horario.
54. `var creditsText by remember { mutableStateOf("") }` — Estado de créditos como texto.
55. `var professorName by remember { mutableStateOf("") }` — Estado del nombre del profesor.
56. Línea en blanco — Separación visual.
57. `LaunchedEffect(professorId, courseId) {` — Carga datos iniciales.
58. `viewModel.getProfessorById(professorId)?.let { professorName = it.name }` — Carga nombre del profesor.
59. `if (isEditing) {` — Si es edición.
60. `viewModel.getCourseById(courseId!!)?.let { course ->` — Carga curso.
61. `title = course.title` — Rellena título.
62. `schedule = course.schedule` — Rellena horario.
63. `creditsText = course.credits.toString()` — Rellena créditos.
64. `}` — Cierra `let`.
65. `}` — Cierra `if`.
66. `}` — Cierra `LaunchedEffect`.
67. Línea en blanco — Separación visual.
68. `Scaffold(` — Estructura base.
69. `topBar = {` — Barra superior.
70. `TopAppBar(` — TopAppBar.
71. `title = { Text(if (isEditing) "Editar curso" else "Agregar curso") },` — Título dinámico.
72. `navigationIcon = {` — Icono volver.
73. `IconButton(onClick = { navController.popBackStack() }) {` — Botón volver.
74. `Icon(Icons.Default.ArrowBack, contentDescription = "Volver")` — Ícono volver.
75. `}` — Cierra botón.
76. `},` — Cierra `navigationIcon`.
77. `colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)` — Color de barra.
78. `)` — Cierra TopAppBar.
79. `}` — Cierra `topBar`.
80. `) { padding ->` — Contenido con padding.
81. `Column(` — Columna principal.
82. `modifier = Modifier` — Modificadores.
83. `.padding(padding)` — Padding del Scaffold.
84. `.padding(16.dp)` — Padding interno.
85. `.verticalScroll(rememberScrollState()),` — Habilita scroll.
86. `verticalArrangement = Arrangement.spacedBy(10.dp)` — Espaciado entre campos.
87. `) {` — Abre contenido.
88. `OutlinedTextField(` — Campo profesor asignado.
89. `value = professorName,` — Muestra nombre.
90. `onValueChange = {},` — No editable.
91. `readOnly = true,` — Campo de solo lectura.
92. `label = { Text("Profesor asignado") },` — Etiqueta.
93. `leadingIcon = { Icon(Icons.Default.School, null) },` — Ícono profesor.
94. `modifier = Modifier.fillMaxWidth()` — Ocupa ancho.
95. `)` — Cierra input.
96. `OutlinedTextField(` — Campo nombre del curso.
97. `value = title,` — Título actual.
98. `onValueChange = { title = it },` — Actualiza estado.
99. `label = { Text("Nombre del curso") },` — Etiqueta.
100. `leadingIcon = { Icon(Icons.Default.Book, null) },` — Ícono curso.
101. `modifier = Modifier.fillMaxWidth()` — Ocupa ancho.
102. `)` — Cierra input.
103. `OutlinedTextField(` — Campo horario.
104. `value = schedule,` — Horario actual.
105. `onValueChange = { schedule = it },` — Actualiza estado.
106. `label = { Text("Horario") },` — Etiqueta.
107. `placeholder = { Text("Ej: Lunes y Miercoles 8:00 AM") },` — Placeholder.
108. `leadingIcon = { Icon(Icons.Default.Schedule, null) },` — Ícono horario.
109. `modifier = Modifier.fillMaxWidth()` — Ocupa ancho.
110. `)` — Cierra input.
111. `OutlinedTextField(` — Campo créditos.
112. `value = creditsText,` — Texto actual.
113. `onValueChange = { creditsText = it.filter { ch -> ch.isDigit() } },` — Solo números.
114. `label = { Text("Creditos") },` — Etiqueta.
115. `leadingIcon = { Icon(Icons.Default.Numbers, null) },` — Ícono números.
116. `modifier = Modifier.fillMaxWidth()` — Ocupa ancho.
117. `)` — Cierra input.
118. Línea en blanco — Separación visual.
119. `Spacer(modifier = Modifier.height(8.dp))` — Separador.
120. Línea en blanco — Separación visual.
121. `Button(` — Botón guardar.
122. `onClick = {` — Acción al guardar.
123. `val credits = creditsText.toIntOrNull()` — Convierte créditos a entero.
124. `if (title.isBlank() || schedule.isBlank() || credits == null) {` — Validación.
125. `Toast.makeText(context, "Completa titulo, horario y creditos validos", Toast.LENGTH_SHORT).show()` — Mensaje de error.
126. `return@Button` — Sale del click.
127. `}` — Cierra `if`.
128. Línea en blanco — Separación visual.
129. `val course = Course(` — Crea objeto curso.
130. `id = courseId ?: 0,` — Usa id si edita.
131. `title = title.trim(),` — Guarda título limpio.
132. `schedule = schedule.trim(),` — Guarda horario limpio.
133. `credits = credits,` — Guarda créditos.
134. `professorId = professorId` — Asigna profesor.
135. `)` — Cierra objeto.
136. Línea en blanco — Separación visual.
137. `if (isEditing) viewModel.updateCourse(course) else viewModel.addCourse(course)` — Actualiza o inserta.
138. `navController.popBackStack()` — Vuelve atrás.
139. `},` — Cierra `onClick`.
140. `modifier = Modifier.fillMaxWidth()` — Botón ancho completo.
141. `) {` — Abre contenido del botón.
142. `Text("Guardar curso")` — Texto del botón.
143. `}` — Cierra botón.
144. `}` — Cierra `Column`.
145. `}` — Cierra `Scaffold`.
146. `}` — Cierra `AddEditCourseScreen`.
