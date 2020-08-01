package com.khudaleyeu.storage4

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_task.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val taskNameTitle = itemView.taskOwnerTxt
    val taskOwnerTitle = itemView.taskNameTxt
    val taskNoteTitle = itemView.taskNoteTxt
}