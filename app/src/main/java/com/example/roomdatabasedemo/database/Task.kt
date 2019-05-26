package com.example.roomdatabasedemo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
//    @ColumnInfo(name = "task")
    val task: String,
//    @ColumnInfo(name = "description")
    val description: String
)