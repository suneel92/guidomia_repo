package com.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.CarAdapter
import com.google.gson.reflect.TypeToken
import com.models.CarData
import com.utils.Constant
import com.utils.FileUtil
import com.utils.GsonUtil.getObjectFromJson

/*
* Holds the complete business logic related to car list and details
* */
class CarListViewModel(application: Application) : AndroidViewModel(application) {
    val carAdapter = CarAdapter()

    init {
        /*
         * Load data from Json
         * */
        FileUtil.getJsonDataFromAsset(getApplication(), Constant.FILE_NAME)?.run {
            carAdapter.setCarList(
                getObjectFromJson(
                    this,
                    object : TypeToken<List<CarData>>() {}.type
                )
            )
        }
    }
}