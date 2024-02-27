package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val TAG = "CalculateWorker"

class CalculateWorker(private val ctx: Context, params: WorkerParameters) :
    CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {
        Toast.makeText(ctx, "calculating", Toast.LENGTH_SHORT).show()

        return withContext(Dispatchers.IO) {
            return@withContext try {
                delay(3_000L)
                Toast.makeText(ctx, "success", Toast.LENGTH_SHORT).show()
                Result.success()
            } catch (throwable: Throwable) {
                Toast.makeText(ctx, "failure", Toast.LENGTH_SHORT).show()
                Result.failure()
            }
        }
    }
}