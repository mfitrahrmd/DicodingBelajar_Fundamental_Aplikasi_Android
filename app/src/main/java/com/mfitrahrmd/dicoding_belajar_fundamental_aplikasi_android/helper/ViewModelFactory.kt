package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui.insert.NoteAddUpdateViewModel
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui.main.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(private val _application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(_application) as T
        } else if (modelClass.isAssignableFrom(NoteAddUpdateViewModel::class.java)) {
            return NoteAddUpdateViewModel(_application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var _INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (_INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    _INSTANCE = ViewModelFactory(application)
                }
            }

            return _INSTANCE!!
        }
    }
}