package com.khudaleyeu.storage4.db

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.khudaleyeu.storage4.MainActivity
import com.khudaleyeu.storage4.task.Task
import java.lang.Exception

class DatabaseHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?,
    version: Int?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        var createTable =
            ("CREATE TABLE $TABLE_NAME ($COLUMN_TASK_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_TASK_NAME TEXT, $COLUMN_TASK_OWNER TEXT, $COLUMN_TASK_NOTE TEXT)")
        db?.execSQL(createTable)
    }

    companion object {
        private val DATABASE_NAME = "task.db"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "Tasks"
        val COLUMN_TASK_ID = "task_id"
        val COLUMN_TASK_NAME = "task_name"
        val COLUMN_TASK_OWNER = "task_owner"
        val COLUMN_TASK_NOTE = "task_note"
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun getTasks(cont: Context): ArrayList<Task> {
        val query = "Select * From $TABLE_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        val tasks = ArrayList<Task>()
        if (cursor.count == 0) {
            Toast.makeText(cont, "Not found ...", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor.moveToNext()) {
                val task = Task()
                task.taskId = cursor.getInt(cursor.getColumnIndex(COLUMN_TASK_ID))
                task.taskNameTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TASK_NAME))
                task.taskOwnerTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TASK_OWNER))
                task.taskNoteTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TASK_NOTE))
                tasks.add(task)
            }
        }
        cursor.close()
        database.close()
        return tasks
    }

    fun addTask(cont: Context, task: Task) {
        val values = ContentValues()
        values.put(COLUMN_TASK_NAME, task.taskNameTitle)
        values.put(COLUMN_TASK_OWNER, task.taskOwnerTitle)
        values.put(COLUMN_TASK_NOTE, task.taskNoteTitle)
        val database = this.writableDatabase
        try {
            database.insert(TABLE_NAME, null, values)
            Toast.makeText(cont, "Task added", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(cont, e.message, Toast.LENGTH_SHORT).show()
        }
        database.close()
    }

    fun updateData(id: Int, taskName: String, ownerName: String, taskNotes: String) {
        val database = this.writableDatabase
        val values = ContentValues()
        if (!taskName.isEmpty()) {
            values.put(COLUMN_TASK_NAME, taskName)
        }
        if (!ownerName.isEmpty()) {
            values.put(COLUMN_TASK_OWNER, ownerName)
        }
        if (!taskNotes.isEmpty()) {
            values.put(COLUMN_TASK_NOTE, taskNotes)
        }
        database.update(TABLE_NAME, values, "$COLUMN_TASK_ID =?", arrayOf(id.toString()))
        database.close()
    }

    fun deleteData(id: Int) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$COLUMN_TASK_ID = ?", arrayOf(id.toString()))
        database.close()
    }
}