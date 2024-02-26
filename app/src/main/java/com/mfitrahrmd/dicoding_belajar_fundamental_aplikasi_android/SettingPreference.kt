package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingPreference private constructor(private val dataStore: DataStore<Preferences>) {
    private val _themeKey = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preference ->
            preference[_themeKey] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preference ->
            preference[_themeKey] = isDarkModeActive
        }
    }

    companion object {
        @Volatile
        private var _INSTANCE: SettingPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreference {
            return _INSTANCE ?: synchronized(this) {
                val instance = SettingPreference(dataStore)
                _INSTANCE = instance

                instance
            }
        }
    }
}