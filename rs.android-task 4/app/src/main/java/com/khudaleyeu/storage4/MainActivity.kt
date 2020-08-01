package com.khudaleyeu.storage4

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.khudaleyeu.storage4.db.DatabaseHandler
import com.khudaleyeu.storage4.task.AddTaskActivity
import com.khudaleyeu.storage4.task.TaskAdapter
import com.khudaleyeu.storage4.task.TaskBase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_task.

class MainActivity : TaskBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHandler = DatabaseHandler(this, null, null, 1)
        viewTask(nameAscSortState, ownerAscSortState, noteAscState, reverseNameSortState,
            reverseOwnerSortState, reverseNoteSortState)
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }

        prefs = getSharedPreferences(NAME, Context.MODE_PRIVATE)
        prefs = getSharedPreferences(OWNER, Context.MODE_PRIVATE)
        prefs = getSharedPreferences(TASK, Context.MODE_PRIVATE)
        prefs = getSharedPreferences(NAME_DESC, Context.MODE_PRIVATE)
        prefs = getSharedPreferences(OWNER_DESC, Context.MODE_PRIVATE)
        prefs = getSharedPreferences(TASK_DESC, Context.MODE_PRIVATE)

        if (nameAscSortState) {
            sortBy(adapter, NAME)
        } else if (ownerAscSortState) {
            sortBy(adapter, OWNER)
        } else if (noteAscState) {
            sortBy(adapter, TASK)
        } else if (reverseNameSortState) {
            sortBy(adapter, NAME_DESC)
        } else if (reverseOwnerSortState) {
            sortBy(adapter, OWNER_DESC)
        } else if (reverseNoteSortState) {
            sortBy(adapter, TASK_DESC)
        }
    }

    private fun sortBy(adapter: TaskAdapter, key: String) {
        tasks.sortWith(compareBy {
            when (key) {
                NAME -> it.taskNameTitle
                OWNER -> it.taskOwnerTitle
                TASK -> it.taskNoteTitle
                NAME_DESC -> it.taskNameTitle.reversed()
                OWNER_DESC -> it.taskOwnerTitle.reversed()
                TASK_DESC -> it.taskNoteTitle.reversed()
                else -> "Nothing was selected"
            }
        })
        adapter.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, SettingsActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    private fun viewTask(
        nameAscSortState: Boolean, ownerAscSortState: Boolean, noteAscState: Boolean,
        reverseName: Boolean, reverseOwner: Boolean, reverseTask: Boolean) {
        if (nameAscSortState && !ownerAscSortState && !noteAscState && !reverseName && !reverseOwner && !reverseTask) {
            sortBy(adapter, NAME)
        } else if (ownerAscSortState && !nameAscSortState && !noteAscState && !reverseName && !reverseOwner && !reverseTask) {
            sortBy(adapter, OWNER)
        } else if (noteAscState && !nameAscSortState && !ownerAscSortState && !reverseName && !reverseOwner && !reverseTask) {
            sortBy(adapter, TASK)
        } else if (!nameAscSortState && !ownerAscSortState && !noteAscState && reverseName && !reverseOwner && !reverseTask) {
            sortBy(adapter, NAME_DESC)
        } else if (!nameAscSortState && !ownerAscSortState && !noteAscState && !reverseName && reverseOwner && !reverseTask) {
            sortBy(adapter, OWNER_DESC)
        } else if (!nameAscSortState && !ownerAscSortState && !noteAscState && !reverseName && !reverseOwner && reverseTask) {
            sortBy(adapter, TASK_DESC)
        } else {
            Toast.makeText(this, "Select one", Toast.LENGTH_SHORT).show()
            prepareView()
        }
    }

    @SuppressLint("WrongConstant")
    private fun prepareView(){
        tasks = databaseHandler.getTasks(this)
        adapter = TaskAdapter(tasks)
        recycler = findViewById(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        recycler.adapter = adapter
    }

    override fun onResume() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        nameAscSortState = prefs.getBoolean(NAME, false)
        ownerAscSortState = prefs.getBoolean(OWNER, false)
        ownerAscSortState = prefs.getBoolean(TASK, false)
        reverseNameSortState = prefs.getBoolean(NAME_DESC, false)
        reverseNameSortState = prefs.getBoolean(OWNER_DESC, false)
        reverseNameSortState = prefs.getBoolean(TASK_DESC, false)
        viewTask(nameAscSortState, ownerAscSortState, ownerAscSortState, reverseNameSortState,
            reverseOwnerSortState, reverseNoteSortState)
        super.onResume()
    }
}
