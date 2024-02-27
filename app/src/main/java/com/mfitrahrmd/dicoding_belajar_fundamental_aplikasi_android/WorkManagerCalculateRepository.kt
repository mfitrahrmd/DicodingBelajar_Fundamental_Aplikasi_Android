package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class WorkManagerCalculateRepository(ctx: Context) {
    private val workManager = WorkManager.getInstance(ctx)

    fun applyCalculate() {
        val builder = OneTimeWorkRequestBuilder<CalculateWorker>()
        workManager.enqueue(builder.build())
    }
}