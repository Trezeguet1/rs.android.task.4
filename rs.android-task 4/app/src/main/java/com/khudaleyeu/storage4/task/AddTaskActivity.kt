package com.khudaleyeu.storage4.task

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.khudaleyeu.storage4.R
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.activity_add_task.taskNoteTitle

class AddTaskActivity : TaskBase(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        saveButton.setOnClickListener {
            if (addtaskNameTitle.text.isEmpty()) {
                Toast.makeText(this, "Enter task", Toast.LENGTH_SHORT).show()
                addtaskNameTitle.requestFocus()
            }
            if (taskNoteTitle.text.isEmpty()) {
                Toast.makeText(this, "Enter note", Toast.LENGTH_SHORT).show()
                taskNoteTitle.requestFocus()
            } else {
                val task = Task()
                task.taskNameTitle = addtaskNameTitle.text.toString()
                task.taskNoteTitle = taskNoteTitle.text.toString()
                if (addtaskNameTitle.text.isEmpty()) {
                    task.taskNameTitle =
                        UNDEFINED
                }
                if (addtaskOwnerTitle.text.isEmpty()) {
                    task.taskOwnerTitle =
                        UNKNOWN
                } else task.taskOwnerTitle = addtaskOwnerTitle.text.toString()
                databaseHandler.addTask(this, task)
                clearFields()
                addtaskNameTitle.requestFocus()
            }
        }

        dismissButton.setOnClickListener {
            clearFields()
            finish()
        }
    }

    private fun clearFields() {
        listOf<Editable>(addtaskNameTitle.text, addtaskOwnerTitle.text, taskNoteTitle.text).forEach {
            it.clear()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
