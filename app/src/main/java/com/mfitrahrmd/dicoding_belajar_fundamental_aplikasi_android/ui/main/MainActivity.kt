package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.helper.ViewModelFactory
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.ui.insert.NoteAddUpdateActivity

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var _noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        val viewModel = obtainViewModel(this@MainActivity)
        viewModel.getAllNotes().observe(this) { noteList ->
            if (noteList != null) {
                _noteAdapter.setListNotes(noteList)
            }
        }
        _noteAdapter = NoteAdapter()
        with(_binding) {
            fabAdd.setOnClickListener {
                val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
                startActivity(intent)
            }
            rvNotes.layoutManager = LinearLayoutManager(this@MainActivity)
            rvNotes.setHasFixedSize(true)
            rvNotes.adapter = _noteAdapter
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }
}