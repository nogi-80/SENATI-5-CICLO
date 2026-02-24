# Proyecto 24-02-2026 - Gestion academica con Jetpack Compose

Aplicacion Android de gestion academica desarrollada con **Jetpack Compose**, **Room**, **Navigation Compose** y arquitectura **MVVM**. Este proyecto sirve como guia practica para aprender los conceptos fundamentales del desarrollo Android moderno.

---

## Requisitos previos

- **Android Studio** Ladybug o superior
- **JDK 11** o superior
- **SDK minimo**: Android 8.0 (API 26)
- **SDK objetivo**: Android 15 (API 36)

---

## Como ejecutar el proyecto

1. Clonar el repositorio:
   ```bash
   git clone git@github.com:carevalojesus/contactsApp.git
   ```
2. Abrir el proyecto en **Android Studio**.
3. Esperar a que Gradle sincronice las dependencias.
4. Conectar un dispositivo fisico o iniciar un emulador.
5. Presionar **Run** (boton verde) o `Shift + F10`.

---

## Estructura del proyecto

```
app/src/main/java/com/equipo/proyecto24022026/
|
|-- MainActivity.kt                   # Punto de entrada de la app
|
|-- data/
|   |-- model/
|   |   |-- Professor.kt             # Entidad Room (profesores)
|   |   |-- Course.kt                # Entidad Room (cursos)
|   |   |-- ProfessorWithCourses.kt  # Relacion profesor -> cursos
|   |-- dao/
|   |   |-- ProfessorDao.kt          # CRUD de profesores
|   |   |-- CourseDao.kt             # CRUD de cursos
|   |   |-- SchoolDao.kt             # Consultas con relaciones
|   |-- database/
|       |-- SchoolDatabase.kt        # Configuracion de la base de datos Room
|
|-- ui/
    |-- navigation/
    |   |-- AppNavigation.kt         # Rutas y navegacion entre pantallas
    |-- screens/
    |   |-- ProfessorListScreen.kt   # Lista de profesores
    |   |-- AddEditProfessorScreen.kt# Agregar/editar profesor
    |   |-- CourseListScreen.kt      # Cursos de un profesor
    |   |-- AddEditCourseScreen.kt   # Agregar/editar curso
    |-- viewmodel/
    |   |-- SchoolViewModel.kt       # Logica de negocio y estado de la UI
    |-- theme/
        |-- Color.kt, Theme.kt, Type.kt  # Tema visual de la app
```

---

## Arquitectura MVVM

La app sigue el patron **Model - View - ViewModel**:

```
[Vista (Screens)]  <-->  [ViewModel]  <-->  [Room DAO]  <-->  [SQLite]
```

| Capa | Archivo | Responsabilidad |
|------|---------|-----------------|
| **Model** | `Professor.kt`, `Course.kt` | Define las entidades Room |
| **Model** | `ProfessorDao.kt`, `CourseDao.kt`, `SchoolDao.kt` | Consultas y operaciones de base de datos |
| **Model** | `SchoolDatabase.kt` | Crea y gestiona la base de datos |
| **ViewModel** | `SchoolViewModel.kt` | Maneja el estado y la logica de negocio |
| **View** | `*Screen.kt` | Composables que renderizan la interfaz |

---

## Conceptos clave explicados

### 1. Room Database (Persistencia de datos)

Room es la libreria oficial de Android para bases de datos SQLite. Se compone de 3 partes:

- **Entity** (`Professor.kt`, `Course.kt`): Clases que representan tablas. Cada propiedad es una columna.
  ```kotlin
  @Entity(tableName = "professors")
  data class Professor(
      @PrimaryKey(autoGenerate = true) val id: Int = 0,
      val name: String,
      val specialty: String,
      // ...
  )
  ```

- **DAO** (`ProfessorDao.kt`, `CourseDao.kt`): Interfaz que define las operaciones (insertar, actualizar, eliminar, consultar).
  ```kotlin
  @Dao
  interface ProfessorDao {
      @Query("SELECT * FROM professors ORDER BY name ASC")
      fun getAllProfessors(): Flow<List<Professor>>

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun insertProfessor(professor: Professor)
  }
  ```

- **Database** (`SchoolDatabase.kt`): Clase abstracta que conecta las entidades con los DAOs. Usa el patron **Singleton** para tener una sola instancia.

### 2. Jetpack Compose (Interfaz de usuario)

En lugar de XML, la UI se construye con funciones `@Composable`:

```kotlin
@Composable
fun ProfessorItem(professor: Professor) {
    Card {
        Text(professor.name)
        Text(professor.specialty)
    }
}
```

Conceptos importantes:
- **`remember`**: Guarda un valor en memoria mientras el composable este activo.
- **`mutableStateOf`**: Crea un estado reactivo. Cuando cambia, la UI se recompone automaticamente.
- **`collectAsState()`**: Convierte un `Flow` en un estado observable por Compose.

### 3. Navigation Compose (Navegacion)

Se definen rutas como strings y se navega entre pantallas:

```kotlin
// Definir rutas
sealed class Screen(val route: String) {
    object Professors : Screen("professors")
    object AddProfessor : Screen("add_professor")
    object EditProfessor : Screen("edit_professor/{professorId}")
}

// Navegar
navController.navigate(Screen.AddProfessor.route)
```

### 4. ViewModel (Estado y logica)

El `ViewModel` sobrevive a cambios de configuracion (como rotar la pantalla) y expone datos como `StateFlow`:

```kotlin
class SchoolViewModel(application: Application) : AndroidViewModel(application) {
    val professors: StateFlow<List<Professor>> = ...

    fun addProfessor(professor: Professor) = viewModelScope.launch {
        professorDao.insertProfessor(professor)
    }
}
```

### 5. Flow y Coroutines (Asincronismo)

- **`Flow`**: Emite datos de forma reactiva. Cuando se inserta un contacto, la lista se actualiza automaticamente.
- **`suspend`**: Marca funciones que se ejecutan en segundo plano sin bloquear la UI.
- **`viewModelScope.launch`**: Lanza una coroutine dentro del ciclo de vida del ViewModel.

### 6. Coil (Carga de imagenes)

Se usa para cargar fotos desde una URI (camara o galeria):

```kotlin
AsyncImage(
    model = photoUri,
    contentDescription = "Foto de perfil",
    modifier = Modifier.size(48.dp).clip(CircleShape)
)
```

### 7. FileProvider (Camara)

Para tomar fotos con la camara, se necesita:
1. Declarar el `FileProvider` en `AndroidManifest.xml`.
2. Definir rutas accesibles en `res/xml/file_paths.xml`.
3. Crear una URI temporal donde se guardara la foto.

---

## Funcionalidades de la app

- **Ver profesores**: Lista con nombre, especialidad y conteo de cursos.
- **Agregar profesor**: Formulario con nombre, especialidad y email.
- **Editar profesor**: Misma pantalla de agregar, precargada con los datos existentes.
- **Eliminar profesor**: Con dialogo de confirmacion; elimina sus cursos asociados.
- **Ver cursos**: Lista de cursos por profesor.
- **Agregar curso**: Formulario con titulo, horario y creditos.
- **Editar curso**: Misma pantalla de agregar, precargada con los datos existentes.
- **Eliminar curso**: Con dialogo de confirmacion.

---

## Dependencias principales

| Libreria | Uso |
|----------|-----|
| `androidx.room` | Base de datos local (SQLite) |
| `androidx.navigation:navigation-compose` | Navegacion entre pantallas |
| `androidx.lifecycle:lifecycle-viewmodel-compose` | ViewModel con Compose |
| `androidx.compose.material3` | Componentes de Material Design 3 |
| `androidx.compose.material:material-icons-extended` | Iconos adicionales |
| `io.coil-kt:coil-compose` | Carga de imagenes desde URI |
| `com.google.devtools.ksp` | Procesador de anotaciones para Room |

Las versiones se gestionan en `gradle/libs.versions.toml` (Version Catalog).

---

## Flujo de datos

```
Usuario toca "Guardar"
        |
        v
AddEditProfessorScreen llama viewModel.addProfessor(professor)
        |
        v
SchoolViewModel ejecuta professorDao.insertProfessor(professor) en una coroutine
        |
        v
Room inserta en SQLite y notifica al Flow
        |
        v
ProfessorListScreen recibe la nueva lista via collectAsState()
        |
        v
La UI se recompone y muestra el nuevo contacto
```

---
