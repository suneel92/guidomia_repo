package com.db

import android.content.Context
import com.models.CarData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
* Holds all room operation methods, This interacts with dao with the help of AppDatabase instance
* */
object RoomRepository {

    /*
    * @param context Context
    * @param cars list of CarData
    * Used to insert the list of car data in the local database
    * */
    suspend fun insert(context: Context, cars: List<CarData>) {
        withContext(Dispatchers.IO) {
            DatabaseBuilder.getInstance(context).carDao().insertCars(cars)
        }
    }

    /*
   * @param context Context
   * Used to get the list of car data from the local database
   * */
    suspend fun get(context: Context): List<CarData> {
        return withContext(Dispatchers.IO) {
            DatabaseBuilder.getInstance(context).carDao().getCars()
        }
    }
}