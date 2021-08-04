package com.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
data class CarData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "cons") val consList: MutableList<String>,
    @ColumnInfo(name = "customer_price") val customerPrice: Int,
    @ColumnInfo(name = "make") val make: String,
    @ColumnInfo(name = "market_price") val marketPrice: Int,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "pros") val prosList: MutableList<String>,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "image") var image: Int,
    val isExpanded: Boolean
)