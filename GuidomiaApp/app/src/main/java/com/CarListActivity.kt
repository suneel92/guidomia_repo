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
        setContentView(activityCarListBinding.root)
        val carListViewModel: CarListViewModel by viewModels()
        activityCarListBinding.viewModel = carListViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::activityCarListBinding.isInitialized) {
            activityCarListBinding.unbind()
        }
    }
}