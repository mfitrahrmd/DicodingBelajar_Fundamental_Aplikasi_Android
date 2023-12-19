package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.util

open class Event<out T>(private val _content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            _content
        }
    }

    fun peekContent(): T = _content
}