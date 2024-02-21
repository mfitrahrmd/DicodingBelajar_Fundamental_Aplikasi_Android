package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

class CuboidModel {
    private var width: Double = 0.0
    private var length: Double = 0.0
    private var height: Double = 0.0

    fun getVolume(): Double = width * length * height

    fun getSurfaceArea(): Double = 2 * ((width * length) + (width * height) + (length * height))

    fun getCircumference(): Double = 4 * (width + length + height)

    fun save(width: Double, length: Double, height: Double) {
        this.width = width
        this.length = length
        this.height = height
    }
}