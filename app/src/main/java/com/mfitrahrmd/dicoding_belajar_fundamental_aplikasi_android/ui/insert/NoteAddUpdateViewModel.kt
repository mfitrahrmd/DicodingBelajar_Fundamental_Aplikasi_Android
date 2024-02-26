package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.database.Note
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {
    private val _noteRepository: NoteRepository = NoteRepository(application)

    fun insert(note: Note) {
        _noteRepository.insert(note)
    }

    fun update(note: Note) {
        _noteRepository.update(note)
    }

    fun delete(note: Note) {
        _noteRepository.delete(note)
    }
}