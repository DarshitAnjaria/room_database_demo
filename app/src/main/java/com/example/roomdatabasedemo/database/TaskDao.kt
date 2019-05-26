package com.example.roomdatabasedemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert
    fun addTask(task: Task)

    @Query("SELECT * FROM task")
    fun getAllTasks(): List<Task>

    @Insert
    fun addMultipleTasks(vararg task: Task)
}