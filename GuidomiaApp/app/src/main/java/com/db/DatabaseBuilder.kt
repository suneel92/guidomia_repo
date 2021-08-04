package com.db

import android.content.Context
import androidx.room.Room
import com.utils.Constant.DB_NAME

object DatabaseBuilder {
    private var INSTANCE: AppDatabase? = null

    /*
    * Used to get DB instance
    * */
    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    /*
    * Build database
    * */
    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        ).build()
}