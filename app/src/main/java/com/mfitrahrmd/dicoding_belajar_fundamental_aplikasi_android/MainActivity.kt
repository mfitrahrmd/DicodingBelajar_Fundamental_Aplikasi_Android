package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)

        val homeFragment = HomeFragment()
        supportFragmentManager.commit {
            add(R.id.fContainer, homeFragment, HomeFragment::class.java.simpleName)
        }
    }
}