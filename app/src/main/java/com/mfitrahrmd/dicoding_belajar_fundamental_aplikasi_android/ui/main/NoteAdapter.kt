package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.database.Note
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ItemNoteBinding
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.helper.NoteDiffCallback
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui.insert.NoteAddUpdateActivity

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val _listNotes = ArrayList<Note>()

    override fun getItemCount(): Int = _listNotes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(_listNotes[position])
    }

    fun setListNotes(listNotes: List<Note>) {
        val diffCallback = NoteDiffCallback(_listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        _listNotes.clear()
        _listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class NoteViewHolder(private val _binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        fun bind(note: Note) {
            with(_binding) {
                tvItemTitle.text = note.title
                tvItemDescription.text = note.description
                tvItemDate.text = note.date
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}