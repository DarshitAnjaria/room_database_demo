package com.example.roomdatabasedemo.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
//    @ColumnInfo(name = "task")
    val task: String,
//    @ColumnInfo(name = "description")
    val description: String
) :Serializable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}