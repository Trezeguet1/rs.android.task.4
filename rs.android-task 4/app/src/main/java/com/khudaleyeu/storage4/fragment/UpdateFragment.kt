package com.khudaleyeu.storage4.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.khudaleyeu.storage4.MainActivity
import com.khudaleyeu.storage4.R
import com.khudaleyeu.storage4.task.Task
import com.khudaleyeu.storage4.task.TaskAdapter
import com.khudaleyeu.storage4.task.TaskBase
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_update, container, false)
        view.updateDataButton.setOnClickListener {
            val task = Task()
            task.taskNameTitle = view.updateTaskNameTitle.text.toString()
            task.taskOwnerTitle = view.updateTaskOwnerTitle.text.toString()
            task.taskNoteTitle = view.updateTaskNoteTitle.text.toString()
            task.taskId = TaskAdapter.currentId

            TaskBase.databaseHandler.updateData(
                task.taskId,
                task.taskNameTitle,
                task.taskOwnerTitle,
                task.taskNoteTitle
            )
            openActivity()
        }

        view.closeButton.setOnClickListener {
            openActivity()
        }
        return view
    }

    private fun openActivity() {
        startActivity(
            Intent(context, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }


}
