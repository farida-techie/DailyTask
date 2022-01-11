package com.malkinfo.dailytask.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.malkinfo.dailytask.R
import com.malkinfo.dailytask.model.UserTask



class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {
    private var taskList: List<UserTask> = ArrayList()

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull holder: TaskViewHolder, position: Int) {
        val newList = taskList[position]
        holder.title.text = newList.userTitle
        holder.date.text = newList.date

    }

    override fun getItemCount(): Int =taskList.size

    fun setNotes(userTask: List<UserTask>) {
        this.taskList = userTask
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(val itemView:View): RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.title)
        var date = itemView.findViewById<TextView>(R.id.date)
    }
}
