package com.utils

import android.content.Context
import java.io.IOException

object FileUtil {

    /*
    * @param context Application level context will also work
    * @param fileName Assets file name
    * Method is used to return file's json into String format
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