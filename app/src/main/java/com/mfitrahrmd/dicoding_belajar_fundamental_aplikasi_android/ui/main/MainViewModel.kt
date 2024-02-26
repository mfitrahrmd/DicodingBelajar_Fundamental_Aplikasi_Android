package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.database.Note
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.repository.NoteRepository

class MainViewModel(application: Application) : ViewModel() {
    private val _noteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes(): LiveData<List<Note>> = _noteRepository.getAllNotes()
}