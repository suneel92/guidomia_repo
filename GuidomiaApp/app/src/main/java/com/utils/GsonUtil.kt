package com.utils

import com.google.gson.Gson
import java.lang.reflect.Type

/*
*  Holds all methods related to Gson for converting objects or Json
* */
object GsonUtil {
    val gson = Gson()

    /*
    * Method is used to parse Json in to provided model
    * */
    fun <T> getObjectFromJson(jsonFileString: String, tokenType: Type): T {
        return gson.fromJson(jsonFileString, tokenType)
    }
}