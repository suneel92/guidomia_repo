package com.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.R
import com.adapter.CarAdapter
import com.db.RoomRepository
import com.google.gson.reflect.TypeToken
import com.models.CarData
import com.utils.Constant
import com.utils.FileUtil
import com.utils.GsonUtil.getObjectFromJson
import kotlinx.coroutines.launch

/*
* Holds the complete business logic related to car list and details
* */
class CarListViewModel(application: Application) : AndroidViewModel(application) {
    val carAdapter = CarAdapter()
    private var carList: List<CarData> = mutableListOf()
    var carMakeList: MutableList<String> = mutableListOf()
    var carModelList: MutableList<String> = mutableListOf()
    val isCarAvailable = MutableLiveData(true)
    val makeItem = MutableLiveData<String>()
    val modelItem = MutableLiveData<String>()
    private var selectedLastMake: String = ""
    private var selectedLastModel: String = ""

    init {
        viewModelScope.launch {
            carList = RoomRepository.get(application)
            if (carList.isEmpty()) {
                FileUtil.getJsonDataFromAsset(getApplication(), Constant.FILE_NAME)?.run {
                    val list: List<CarData>? = getObjectFromJson(
                        this,
                        object : TypeToken<List<CarData>>() {}.type
                    )
                    list?.let {
                        carList = it
                        modifyList()
                        RoomRepository.insert(application, carList)
                    }
                }
            }
            setDataInCategoryList()
            carAdapter.setCarList(carList)
        }
    }

    /*
    * Used to set name of make and model in the respective list
    * */
    private fun setDataInCategoryList() {
        carList.forEach {
            if (!carMakeList.contains(it.make)) {
                carMakeList.add(it.make)
            }
            if (!carModelList.contains(it.model)) {
                carModelList.add(it.model)
            }
        }
    }

    /*
    * @param make Car's make
    * Used to filter the list as per selected make
    * */
    fun onMakeSelection(make: String) {
        val makeCarList: MutableList<CarData> = mutableListOf()
        selectedLastMake = make
        carList.forEach {
            if (make == it.make && (selectedLastModel.isEmpty()
                        || (selectedLastModel.isNotEmpty() && selectedLastModel == it.model))
            ) {
                makeCarList.add(it)
            }
        }
        carAdapter.setCarList(makeCarList)
        validateEmptyListMessage(makeCarList.size)
    }

    /*
   * @param model Car's model
   * Used to filter the list as per selected model
   * */
    fun onModelSelection(model: String) {
        val modelCarList: MutableList<CarData> = mutableListOf()
        selectedLastModel = model
        carList.forEach {
            if (model == it.model && (selectedLastMake.isEmpty()
                        || (selectedLastMake.isNotEmpty() && selectedLastMake == it.make))
            ) {
                modelCarList.add(it)
            }
        }
        carAdapter.setCarList(modelCarList)
        validateEmptyListMessage(modelCarList.size)
    }

    /*
     * @param listSize size of list
     * Validate that empty list message should be show or not
     * */
    private fun validateEmptyListMessage(listSize: Int) = isCarAvailable.postValue(listSize > 0)

    /*
    * Modify the list to validate images and remove empty fields from pros and cons
    * */
    private fun modifyList() {
        carList.forEach { carData ->
            carData.prosList.removeAll { it.isBlank() }
            carData.consList.removeAll { it.isBlank() }
            when (carData.make) {
                Constant.LAND -> carData.image = R.drawable.img_range_rover
                Constant.ALPINE -> carData.image = R.drawable.img_alpine_roadster
                Constant.BMW -> carData.image = R.drawable.img_bmw_330i
                Constant.MERCEDES -> carData.image = R.drawable.img_mercedez_benz_glc
                else -> carData.image = R.drawable.img_tacoma
            }
        }
    }
}