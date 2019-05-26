package com.example.roomdatabasedemo.view


import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.solver.widgets.Helper

import com.example.roomdatabasedemo.R
import com.example.roomdatabasedemo.database.Task
import com.example.roomdatabasedemo.database.TaskDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.coroutines.launch

class AddTask : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fabSave.setOnClickListener {

            val newTask = etTask.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (newTask.isEmpty()){
                etTask.setError("Please define task")
                etTask.requestFocus()
                Snackbar.make(relative_add_task, "Please define task", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (description.isEmpty()){
                etDescription.setError("Please enter description")
                etDescription.requestFocus()
                Snackbar.make(relative_add_task, "Please enter description", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            launch {
                val task = Task(newTask, description)

                context?.let {

                    TaskDatabase(it).getTaskDao().addTask(task)
//                    Snackbar.make(relative_add_task, "Task Added!", Snackbar.LENGTH_LONG).show()
                    it.snackbar(relative_add_task, "Task Added!", Snackbar.LENGTH_LONG)
                    etTask.text = null
                    etDescription.text = null
                    etTask.requestFocus()

                }
            }
        }
    }

}
