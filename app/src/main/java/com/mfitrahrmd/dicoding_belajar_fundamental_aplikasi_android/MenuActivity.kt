package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        with(_binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { v, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    Toast.makeText(this@MenuActivity, searchView.text, Toast.LENGTH_SHORT).show()

                    false
                }
        }
    }
}