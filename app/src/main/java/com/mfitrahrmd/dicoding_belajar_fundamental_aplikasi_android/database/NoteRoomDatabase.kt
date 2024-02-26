package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class NoteRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var _INSTANCE: NoteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NoteRoomDatabase {
            if (_INSTANCE == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    _INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteRoomDatabase::class.java,
                        "notes_app"
                    ).build()
                }
            }

            return _INSTANCE!!
        }
    }
}