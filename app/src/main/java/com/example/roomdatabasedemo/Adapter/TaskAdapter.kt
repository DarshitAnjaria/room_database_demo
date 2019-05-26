package com.example.roomdatabasedemo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.Database.Task
import com.example.roomdatabasedemo.R
import com.example.roomdatabasedemo.View.HomeFragmentDirections
import kotlinx.android.synthetic.main.itemview.view.*

class TaskAdapter(val tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.itemview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.view.tvTaskTitle.text = tasks[position].task
        holder.view.tvTaskDescription.text = tasks[position].description

        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionAddTask()
            action.task = tasks[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class TaskViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}