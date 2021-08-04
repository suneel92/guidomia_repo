package com.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
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
    private var carList: List<CarData> = mutableListOf()
    var carMakeList: MutableList<String> = mutableListOf()
    var carModelList: MutableList<String> = mutableListOf()
    val isCarAvailable: MutableLiveData<Boolean> = MutableLiveData(true)
    val makeItem: MutableLiveData<String> = MutableLiveData()
    val modelItem: MutableLiveData<String> = MutableLiveData()
    private var selectedLastMake: String = ""
    private var selectedLastModel: String = ""

    init {
        /*
        * Load data from Json
        * */
        FileUtil.getJsonDataFromAsset(getApplication(), Constant.FILE_NAME)?.run {
            carList = getObjectFromJson(
                this,
                object : TypeToken<List<CarData>>() {}.type
            )
            setDataInCategoryList()
            carAdapter.setCarList(carList)
        }
    }

    /*
    * Used to set name of make and model in the respective list
    * */
    private fun setDataInCategoryList() {
        for (car in carList) {
            if (!carMakeList.contains(car.make)) {
                carMakeList.add(car.make)
            }
            if (!carModelList.contains(car.model)) {
                carModelList.add(car.model)
            }
        }
    }

    /*
    * Used to filter the list as per selected make
    * */
    fun onMakeSelection(make: String) {
        val makeCarList: MutableList<CarData> = mutableListOf()
        selectedLastMake = make
        for (car in carList) {
            if (make == car.make && (selectedLastModel.isEmpty()
                        || (selectedLastModel.isNotEmpty() && selectedLastModel == car.model))
            ) {
                makeCarList.add(car)
            }
        }
        carAdapter.setCarList(makeCarList)
        validateEmptyListMessage(makeCarList.size)
    }

    /*
   * Used to filter the list as per selected model
   * */
    fun onModelSelection(model: String) {
        val modelCarList: MutableList<CarData> = mutableListOf()
        selectedLastModel = model
        for (car in carList) {
            if (model == car.model && (selectedLastMake.isEmpty()
                        || (selectedLastMake.isNotEmpty() && selectedLastMake == car.make))
            ) {
                modelCarList.add(car)
            }
        }
        carAdapter.setCarList(modelCarList)
        validateEmptyListMessage(modelCarList.size)
    }

    /*
     * Validate that empty list message should be show or not
     * */
    private fun validateEmptyListMessage(listSize: Int) {
        isCarAvailable.postValue(listSize > 0)
    }
}