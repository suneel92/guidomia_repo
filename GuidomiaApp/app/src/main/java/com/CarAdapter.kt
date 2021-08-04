package com

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.databinding.ItemCarBinding
import com.models.CarData
import com.utils.Constant.ALPINE
import com.utils.Constant.BMW
import com.utils.Constant.LAND
import com.utils.Constant.MERCEDES

class CarAdapter : RecyclerView.Adapter<CarAdapter.CarInnerViewHolder>() {

    private var cars = mutableListOf<CarData>()

    /*
    * Set car list to show in the view
    * */
    fun setCarList(carData: List<CarData>?) {
        carData?.let {
            this.cars = it.toMutableList()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarInnerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarBinding.inflate(inflater, parent, false)
        return CarInnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarInnerViewHolder, position: Int) {
        holder.binding.helper = cars[position]
        when (cars[position].make) {
            LAND -> holder.setImage(R.drawable.img_range_rover)
            ALPINE -> holder.setImage(R.drawable.img_alpine_roadster)
            BMW -> holder.setImage(R.drawable.img_bmw_330i)
            MERCEDES -> holder.setImage(R.drawable.img_mercedez_benz_glc)
            else -> holder.setImage(R.drawable.img_tacoma)
        }
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    inner class CarInnerViewHolder(val binding: ItemCarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /*
        * Used to setup image resource
        * */
        fun setImage(image: Int) {
            binding.imgCar.setImageResource(image)
        }
    }
}