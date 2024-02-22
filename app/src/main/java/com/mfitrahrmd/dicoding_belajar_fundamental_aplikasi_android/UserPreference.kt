package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.Context

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val AGE = "age"
        private const val PHONE_NUMBER = "phone"
        private const val IS_LOVE_MU = "isloveMU"
    }

    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(userModel: UserModel) {
        val editor = preference.edit()
        with(userModel) {
            editor.apply {
                putString(NAME, name)
                putString(EMAIL, email)
                putInt(AGE, age)
                putString(PHONE_NUMBER, phoneNumber)
                putBoolean(IS_LOVE_MU, isLoveMU)
                apply()
            }
        }
    }

    fun getUser(): UserModel {
        with(preference) {
            return UserModel(
                getString(NAME, ""),
                getString(EMAIL, ""),
                getInt(AGE, 0),
                getString(PHONE_NUMBER, ""),
                getBoolean(IS_LOVE_MU, false)
            )
        }
    }
}