package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.Context

internal object FileHelper {
    fun writeToFile(fileModel: FileModel, context: Context) {
        context.openFileOutput(fileModel.fileName, Context.MODE_PRIVATE).use {
            it.write(fileModel.data?.toByteArray())
        }
    }

    fun readFromFile(fileName: String, context: Context): FileModel {
        val fileModel: FileModel = FileModel()
        fileModel.fileName = fileName
        fileModel.data =
            context.openFileInput(fileModel.fileName).bufferedReader().useLines { lines ->
                lines.fold("") { some, text ->
                    "$some\n$text"
                }
            }

        return fileModel
    }
}