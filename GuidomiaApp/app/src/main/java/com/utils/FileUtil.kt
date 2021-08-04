package com.utils

import android.content.Context
import java.io.IOException

object FileUtil {

    /*
    * Method is used to return file's json in to String format
    * */
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}