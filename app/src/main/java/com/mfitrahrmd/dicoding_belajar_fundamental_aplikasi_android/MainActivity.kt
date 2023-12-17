package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(_binding.topAppBar)
        setContentView(_binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fContainer, MenuFragment())
                    .addToBackStack(null)
                    .commit()

                true
            }
            R.id.menu2 -> {
                startActivity(Intent(this@MainActivity, MenuActivity::class.java))

                true
            }
            else -> false
        }
    }
}