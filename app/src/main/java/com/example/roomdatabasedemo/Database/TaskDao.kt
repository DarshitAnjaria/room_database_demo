package com.example.roomdatabasedemo.Database

import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM task ORDER BY id DESC")
    suspend fun getAllTasks(): List<Task>

    @Insert
    suspend fun addMultipleTasks(vararg task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}