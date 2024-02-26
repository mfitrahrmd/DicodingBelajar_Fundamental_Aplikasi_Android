package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.helper

import androidx.recyclerview.widget.DiffUtil
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.database.Note

class NoteDiffCallback(private val _oldNoteList: List<Note>, private val _newNoteList: List<Note>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = _oldNoteList.size

    override fun getNewListSize(): Int = _newNoteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return _oldNoteList[oldItemPosition].id == _newNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = _oldNoteList[oldItemPosition]
        val newNote = _newNoteList[oldItemPosition]

        return oldNote.title == newNote.title && oldNote.description == newNote.description
    }
}