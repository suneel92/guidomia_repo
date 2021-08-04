package com.db

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.utils.GsonUtil

class Converters {

    @TypeConverter
    fun restoreList(listOfString: String?): List<String?>? {
        return GsonUtil.gson.fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<String?>?): String? {
        return GsonUtil.gson.toJson(listOfString)
    }
}