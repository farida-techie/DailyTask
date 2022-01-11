package com.malkinfo.dailytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.malkinfo.dailytask.adapter.TaskListAdapter
import com.malkinfo.dailytask.model.UserTask
import com.malkinfo.dailytask.viewmodel.TaskViewModel
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.annotation.Nullable
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders




class MainActivity : AppCompatActivity() {

    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userTaskAdapter: TaskListAdapter


    /**set viewModel*/
    private lateinit var taskViewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /** get Id */
        addsBtn = findViewById(R.id.addTask)
        recv = findViewById(R.id.taskRec)


        /** set User Task List And Adapter*/
        userTaskAdapter = TaskListAdapter()
        recv.layoutManager = LinearLayoutManager(this)
        recv.setHasFixedSize(true);
        recv.adapter = userTaskAdapter

        /** set ViewModel*/
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        taskViewModel.getAllTask()?.observe(this,
            { userTask -> userTaskAdapter.setNotes(userTask as List<UserTask>); })


        /** user Add task Button*/
        addsBtn.setOnClickListener {
            addInfo()
        }


    }

    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.user_task_view,null)
        /**setting view*/
        val taskTitle = v.findViewById<EditText>(R.id.addTitle)
        val taskDate = v.findViewById<EditText>(R.id.addDate)
        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)

        addDialog.setPositiveButton("Ok") {

                dialog,_->
            val titles = taskTitle.text.toString()
            val dates = taskDate.text.toString()
            if(titles.isNotEmpty() && dates.isNotEmpty()){
                var userTask = UserTask(userTitle = titles,date = dates)
                taskViewModel.insert(userTask)
                Toast.makeText(this,"Task added",Toast.LENGTH_SHORT).show()
                dialog.dismiss()

            } else{
                Toast.makeText(this,"No task was entered",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

        }


        addDialog.setNegativeButton("Cancel") {

                dialog,_->
            Toast.makeText(this,"Canceled",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        addDialog.create()
        addDialog.show()

    }
}