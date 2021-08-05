package com.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.databinding.ItemCarBinding
import com.models.CarData

class CarAdapter : RecyclerView.Adapter<CarAdapter.CarInnerViewHolder>() {

    private var cars = mutableListOf<CarData>()
    private var previousExpandedPosition = -1
    private var mExpandedPosition = 0

    /*
    * @param carData List of CarData to show on UI
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
        holder.setProsCons(cars[position])
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    inner class CarInnerViewHolder(val binding: ItemCarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /*
        * @param data CarData's object to get the inner lists
        * Used to setup inner recycler views and collapsing things
        * */
        fun setProsCons(data: CarData) {
            setCollapseAbility()
            binding.listPro.adapter = BulletPointAdapter().setPointsList(data.prosList)
            binding.listCon.adapter = BulletPointAdapter().setPointsList(data.consList)
        }

        /*
        * Used to setup Collapse and Expand Ability
        * */
        private fun setCollapseAbility() {
            val isExpanded = (adapterPosition == mExpandedPosition)
            binding.layDetails.visibility = if (isExpanded) View.VISIBLE else View.GONE
            binding.layDetails.isActivated = isExpanded

            if (isExpanded) previousExpandedPosition = adapterPosition

            binding.layDetails.rootView.setOnClickListener {
                mExpandedPosition = if (isExpanded) -1 else adapterPosition
                notifyItemChanged(previousExpandedPosition)
                notifyItemChanged(adapterPosition)
            }
        }
    }
}