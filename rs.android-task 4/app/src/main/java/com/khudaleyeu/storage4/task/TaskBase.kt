package com.khudaleyeu.storage4.task

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.khudaleyeu.storage4.db.DatabaseHandler

open class TaskBase : AppCompatActivity(){
    companion object{
        val UNKNOWN = "Unknown"
        val UNDEFINED = "Undefined"
        val NAME = "name"
        val OWNER = "owner"
        val TASK = "task"
        val NAME_DESC = "nameDesc"
        val OWNER_DESC = "ownerDesc"
        val TASK_DESC = "taskDesc"

        var nameAscSortState: Boolean = false
        var ownerAscSortState: Boolean = false
        var noteAscState: Boolean = false
        var reverseNameSortState: Boolean = false
        var reverseOwnerSortState: Boolean = false
        var reverseNoteSortState: Boolean = false

        lateinit var databaseHandler: DatabaseHandler
        var tasks = ArrayList<Task>()
        lateinit var adapter: TaskAdapter
        lateinit var prefs: SharedPreferences
        lateinit var recycler: RecyclerView
    }
}