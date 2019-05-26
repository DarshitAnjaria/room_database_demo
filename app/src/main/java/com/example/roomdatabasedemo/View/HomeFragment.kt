package com.example.roomdatabasedemo.View


import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.roomdatabasedemo.Adapter.TaskAdapter

import com.example.roomdatabasedemo.R
import com.example.roomdatabasedemo.Database.TaskDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        launch {
            context?.let {
                val tasks = TaskDatabase(it).getTaskDao().getAllTasks()
                if (tasks.size == 0){
                    tvEmpty.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                    return@launch
                }

                recyclerView.adapter = TaskAdapter(tasks)

            }
        }

        fabAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionAddTask()
            Navigation.findNavController(it).navigate(action)
        }
    }

}
