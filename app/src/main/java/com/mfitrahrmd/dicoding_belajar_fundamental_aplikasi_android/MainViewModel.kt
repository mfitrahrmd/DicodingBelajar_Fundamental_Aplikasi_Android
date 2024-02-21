package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import androidx.lifecycle.ViewModel

class MainViewModel(private val _cuboidModel: CuboidModel) : ViewModel() {
    fun getCircumference() = _cuboidModel.getCircumference()

    fun getSurfaceArea() = _cuboidModel.getSurfaceArea()

    fun getVolume() = _cuboidModel.getVolume()

    fun save(width: Double, length: Double, height: Double) {
        _cuboidModel.save(width, length, height)
    }
}