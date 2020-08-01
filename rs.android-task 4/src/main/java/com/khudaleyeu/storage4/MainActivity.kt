package com.khudaleyeu.storage4

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceFragment
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var databaseHandler: DatabaseHandler
         var nameAscSortState : String = ""
         var ownerAscSortState : String = ""
        internal var name: CharSequence? = null
        val tasks = ArrayList<Task>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHandler = DatabaseHandler(this, null, null,1)

        viewTask()

        floatingActionButton.setOnClickListener {
            val plusActivity = Intent(this, AddTaskActivity::class.java)
            startActivity(plusActivity)
        }

        sortButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("WrongConstant")
    private fun viewTask(){
        var isActive = nameAscSortState.contains("false") && ownerAscSortState.contains("false")
        Toast.makeText(this, "is active status $isActive", Toast.LENGTH_LONG).show()
        if(isActive){
        val taskList = databaseHandler.getTasks(this)
        val adapter = TaskAdapter(this, taskList)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager
        recyclerView.adapter = adapter
    }else{
//            Toast.makeText(this, "Not selected", 20).show()
            val taskList = databaseHandler.getTasks(this)
            val sortedTask = taskList.toMutableList().sortedBy { it.taskNameTitle.toString() }

            sortedTask.forEach { it ->
                val task = Task()
                task.taskNameTitle = it.taskNameTitle
                task.taskOwnerTitle = it.taskOwnerTitle
                task.taskId = it.taskId
                task.taskNoteTitle = it.taskNoteTitle
                tasks.add(task)
            }

            val adapter = TaskAdapter(this, tasks)
            val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager
            recyclerView.adapter = adapter
        }}

    override fun onResume() {
        viewTask()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        nameAscSortState = prefs.getBoolean("name", false).toString()
        ownerAscSortState = prefs.getBoolean("owner", false).toString()
        super.onResume()
    }




}
