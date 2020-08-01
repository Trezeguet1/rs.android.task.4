package com.khudaleyeu.storage4.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.khudaleyeu.storage4.R
import com.khudaleyeu.storage4.ViewHolder
import com.khudaleyeu.storage4.fragment.UpdateFragment


class TaskAdapter(val tasks: ArrayList<Task>): RecyclerView.Adapter<ViewHolder>() {

    companion object{
        var currentId = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_task, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task: Task = tasks[position]
        holder.taskNameTxtField.text = task.taskNameTitle
        holder.taskOwnerTxtField.text = task.taskOwnerTitle
        holder.taskNoteTxtField.text = task.taskNoteTitle

        holder.updateButton.setOnClickListener {
            currentId = task.taskId
            var activity = it.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.recyclerViewMain, UpdateFragment()).addToBackStack(null).commit()
        }

        holder.del_btn.setOnClickListener {
            currentId = task.taskId
            TaskBase.databaseHandler.deleteData(currentId)
            TaskBase.adapter.notifyItemRemoved(currentId)
            TaskBase.adapter.notifyDataSetChanged()
            notifyItemRemoved(currentId)
        }
    }
}