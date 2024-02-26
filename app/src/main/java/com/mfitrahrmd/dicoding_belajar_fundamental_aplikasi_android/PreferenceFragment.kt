package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat

class PreferenceFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var _name: String
    private lateinit var _email: String
    private lateinit var _age: String
    private lateinit var _phone: String
    private lateinit var _love: String

    private lateinit var _namePreference: EditTextPreference
    private lateinit var _emailPreference: EditTextPreference
    private lateinit var _agePreference: EditTextPreference
    private lateinit var _phonePreference: EditTextPreference
    private lateinit var _lovePreference: CheckBoxPreference

    companion object {
        private const val DEFAULT_VALUE = "None"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun init() {
        _name = resources.getString(R.string.key_name)
        _email = resources.getString(R.string.key_email)
        _age = resources.getString(R.string.key_age)
        _phone = resources.getString(R.string.key_phone)
        _love = resources.getString(R.string.key_love)

        _namePreference = findPreference<EditTextPreference>(_name) as EditTextPreference
        _emailPreference = findPreference<EditTextPreference>(_email) as EditTextPreference
        _agePreference = findPreference<EditTextPreference>(_age) as EditTextPreference
        _phonePreference = findPreference<EditTextPreference>(_phone) as EditTextPreference
        _lovePreference = findPreference<CheckBoxPreference>(_love) as CheckBoxPreference
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        if (sh != null) {
            _namePreference.summary = sh.getString(_name, DEFAULT_VALUE)
            _emailPreference.summary = sh.getString(_email, DEFAULT_VALUE)
            _agePreference.summary = sh.getString(_age, DEFAULT_VALUE)
            _phonePreference.summary = sh.getString(_phone, DEFAULT_VALUE)
            _lovePreference.isChecked = sh.getBoolean(_love, false)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if (key == _name) {
            _namePreference.summary = sharedPreferences.getString(_name, DEFAULT_VALUE)
        }
        if (key == _email) {
            _emailPreference.summary = sharedPreferences.getString(_email, DEFAULT_VALUE)
        }
        if (key == _age) {
            _agePreference.summary = sharedPreferences.getString(_age, DEFAULT_VALUE)
        }
        if (key == _phone) {
            _phonePreference.summary = sharedPreferences.getString(_phone, DEFAULT_VALUE)
        }
        if (key == _love) {
            _lovePreference.isChecked = sharedPreferences.getBoolean(_love, false)
        }
    }
}