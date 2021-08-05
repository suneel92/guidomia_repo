package com.utils

import com.google.gson.Gson
import java.lang.reflect.Type

/*
*  Holds all methods related to Gson for converting objects or Json
* */
object GsonUtil {
    val gson = Gson()

    /*
    * @param jsonFileString Json string to convert in the object
    * @param tokenType type of the desired object
    * Method is used to parse Json into provided model
    * */
    fun <T> getObjectFromJson(jsonFileString: String, tokenType: Type): T? {
        try {
            return gson.fromJson(jsonFileString.replace("\\", ""), tokenType)
        } catch (e: Exception) {
        }
        return null
    }
}