package com

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.databinding.ActivityCarListBinding
import com.view_models.CarListViewModel

/*
* Application's Index Activity, show the list of Cars and details
* */
class CarListActivity : AppCompatActivity() {
    private lateinit var activityCarListBinding: ActivityCarListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCarListBinding = ActivityCarListBinding.inflate(layoutInflater)
        activityCarListBinding.lifecycleOwner = this
        setContentView(activityCarListBinding.root)
        val carListViewModel: CarListViewModel by viewModels()
        activityCarListBinding.viewModel = carListViewModel
        observeData(carListViewModel)
    }

    /*
    * Observed data will be notify here
    * */
    private fun observeData(viewModel: CarListViewModel) {
        viewModel.makeItem.observe(this, {
            viewModel.onMakeSelection(it)
        })
        viewModel.modelItem.observe(this, {
            viewModel.onModelSelection(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::activityCarListBinding.isInitialized) {
            activityCarListBinding.unbind()
        }
    }
}