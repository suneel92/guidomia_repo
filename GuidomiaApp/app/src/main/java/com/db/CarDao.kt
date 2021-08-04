package com.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.models.CarData

@Dao
interface CarDao {
    @Query("SELECT * FROM car")
    suspend fun getCars(): List<CarData>

    @Insert
    suspend fun insertCars(cars: List<CarData>)
}