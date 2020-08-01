package com.khudaleyeu.storage4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        saveButton.setOnClickListener {
            if(addtaskNameTitle.text.isEmpty()){
                Toast.makeText(this, "Enter task", Toast.LENGTH_SHORT).show()
                addtaskNameTitle.requestFocus()
            }else{
                val task = Task()
                task.taskNameTitle = addtaskNameTitle.text.toString()
                if(addtaskOwnerTitle.text.isEmpty()) task.taskOwnerTitle = "unknown" else task.taskOwnerTitle = addtaskOwnerTitle.text.toString()
                MainActivity.databaseHandler.addTask(this, task)
                clearFields()
                addtaskNameTitle.requestFocus()
            }
        }

        dismissButton.setOnClickListener {
            clearFields()
            finish()
        }
    }

    private fun clearFields(){
        addtaskNameTitle.text.clear()
        addtaskOwnerTitle.text.clear()
        addtaskNoteTitle.text.clear()
    }
}
