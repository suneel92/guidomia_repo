package com.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.databinding.ItemBulletRowBinding

class BulletPointAdapter : RecyclerView.Adapter<BulletPointAdapter.BulletInnerViewHolder>() {

    private var points = mutableListOf<String>()

    /*
    * @param points List of strings to show in points
    * Set points list to show in the view
    * */
    fun setPointsList(points: List<String>?): BulletPointAdapter {
        points?.let {
            this.points = it.toMutableList()
            notifyDataSetChanged()
        }
        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BulletInnerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBulletRowBinding.inflate(inflater, parent, false)
        return BulletInnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BulletInnerViewHolder, position: Int) {
        holder.binding.content = points[position]
    }

    override fun getItemCount(): Int {
        return points.size
    }

    inner class BulletInnerViewHolder(val binding: ItemBulletRowBinding) :
        RecyclerView.ViewHolder(binding.root)
}