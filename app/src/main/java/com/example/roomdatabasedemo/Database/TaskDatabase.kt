package com.example.roomdatabasedemo.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 1
)

abstract class TaskDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    companion object{

        //Volatile object because of it calls immediately on any thread
        @Volatile private var instance : TaskDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java,
            "taskdatabase"
        ).build()
    }
}