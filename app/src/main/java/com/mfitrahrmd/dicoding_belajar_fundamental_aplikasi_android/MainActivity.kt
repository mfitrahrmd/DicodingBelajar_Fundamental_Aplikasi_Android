package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding

const val USER_PREFERENCE_NAME = "user"

private object UserPreferenceKeys {
    val IS_MARRIED = booleanPreferencesKey("is_married")
}

private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCE_NAME
)

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        WorkManagerCalculateRepository(this).applyCalculate()
    }
}