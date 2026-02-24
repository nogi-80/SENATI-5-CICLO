package com.equipo.proyecto24022026.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.equipo.proyecto24022026.data.dao.CourseDao
import com.equipo.proyecto24022026.data.dao.ProfessorDao
import com.equipo.proyecto24022026.data.dao.SchoolDao
import com.equipo.proyecto24022026.data.model.Course
import com.equipo.proyecto24022026.data.model.Professor

@Database(entities = [Professor::class, Course::class], version = 1, exportSchema = false)
abstract class SchoolDatabase : RoomDatabase() {

    abstract fun professorDao(): ProfessorDao
    abstract fun courseDao(): CourseDao
    abstract fun schoolDao(): SchoolDao

    companion object {
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getDatabase(context: Context): SchoolDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
