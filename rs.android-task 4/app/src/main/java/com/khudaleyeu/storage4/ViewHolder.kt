package com.khudaleyeu.storage4

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.layout_task.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val taskNameTxtField = itemView.taskOwnerTxt
    val taskOwnerTxtField = itemView.taskNameTxt
    val taskNoteTxtField = itemView.taskNoteTxt
    val updateButton = itemView.update_button
    val del_btn = itemView.delete_button
}