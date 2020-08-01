package com.khudaleyeu.storage4

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(cont: Context, val tasks : ArrayList<Task>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_task, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task : Task = tasks[position]
        holder.taskNameTitle.text = task.taskNameTitle
        holder.taskOwnerTitle.text = task.taskOwnerTitle
        holder.taskNoteTitle.text = task.taskNoteTitle
    }
}