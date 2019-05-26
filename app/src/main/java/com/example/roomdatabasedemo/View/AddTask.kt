package com.example.roomdatabasedemo.View


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation

import com.example.roomdatabasedemo.R
import com.example.roomdatabasedemo.Database.Task
import com.example.roomdatabasedemo.Database.TaskDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.coroutines.launch

class AddTask : BaseFragment() {

    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            task = AddTaskArgs.fromBundle(it).task

            etTask.setText(task?.task)
            etDescription.setText(task?.description)
        }

        fabSave.setOnClickListener { view ->

            val newTask = etTask.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (newTask.isEmpty()) {
                etTask.setError("Please define task")
                etTask.requestFocus()
                Snackbar.make(relative_add_task, "Please define task", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                etDescription.setError("Please enter description")
                etDescription.requestFocus()
                Snackbar.make(relative_add_task, "Please enter description", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            launch {

                context?.let {

                    val mTask = Task(newTask, description)

                    if (mTask == null){
                        TaskDatabase(it).getTaskDao().addTask(mTask)
                        Snackbar.make(relative_add_task, "Task Added!", Snackbar.LENGTH_LONG)
                            .setAction("VIEW", View.OnClickListener {
                                val action = AddTaskDirections.actionSaveTask()
                                Navigation.findNavController(view).navigate(action)
                            }).show()

                        etTask.text = null
                        etDescription.text = null
                        etTask.requestFocus()
                    }else{
                        mTask.id = task!!.id
                        TaskDatabase(it).getTaskDao().updateTask(mTask)
                        Snackbar.make(relative_add_task, "Task Updated!", Snackbar.LENGTH_LONG)
                            .setAction("VIEW", View.OnClickListener {
                                val action = AddTaskDirections.actionSaveTask()
                                Navigation.findNavController(view).navigate(action)
                            }).show()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_task -> if (task != null){
                deleteNote()
            }else{
                context?.snackbar(relative_add_task, "Cannot delete task!", Snackbar.LENGTH_LONG)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote(){
        AlertDialog.Builder(context).apply {
            setMessage("Are you sure?")
            setPositiveButton("YES"){ _, _ ->
                launch {
                    TaskDatabase(context).getTaskDao().deleteTask(task!!)
                    val action = AddTaskDirections.actionSaveTask()
                    Navigation.findNavController(view!!).navigate(action)
                }
            }
            setNegativeButton("NO"){ _, _ ->

            }
        }.create().show()
    }

}
