package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.database.Note
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.database.NoteDao
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val _notesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        _notesDao = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> = _notesDao.getAllNotes()

    fun insert(note: Note) {
        executorService.execute {
            _notesDao.insert(note)
        }
    }

    fun delete(note: Note) {
        executorService.execute {
            _notesDao.delete(note)
        }
    }

    fun update(note: Note) {
        executorService.execute {
            _notesDao.update(note)
        }
    }
}