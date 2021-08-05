package com.utils

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.R

/*
* @param view RecyclerView
* @param isAddDivider allows to add divider
* Set divider between RecyclerView items
*/
@BindingAdapter("android:divider")
fun setDivider(view: RecyclerView, isAddDivider: Boolean) {
    if (isAddDivider) {
        view.addItemDecoration(object :
            DividerItemDecoration(view.context, VERTICAL) {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                // Do not draw the divider after last position
                val dividerLeft = parent.paddingLeft
                val dividerRight = parent.width - parent.paddingRight
                val childCount = parent.childCount
                for (i in 0..childCount - 2) {
                    val child: View = parent.getChildAt(i)
                    val params =
                        child.layoutParams as RecyclerView.LayoutParams
                    val dividerTop: Int = child.bottom + params.bottomMargin
                    val dividerBottom = dividerTop + (drawable?.intrinsicHeight ?: 0)
                    drawable?.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
                    drawable?.draw(c)
                }
            }
        }.apply {
            setDrawable(
                ContextCompat.getDrawable(
                    view.context,
                    R.drawable.sh_divider
                )!!
            )
        })
    }
}

/*
* @param view TextView
* @param price Price with the multiplication of 1000
* Set price string into TextView
*/
@SuppressLint("SetTextI18n")
@BindingAdapter("android:price_string")
fun setPriceString(view: TextView, price: Int) {
    view.text =
        view.context.getString(R.string.price) + (price / 1000) + view.context.getString(R.string.k)
}

/*
* @param view AutoCompleteTextView
* @param datList List to set in the view's adapter
* Set adapter into AutoCompleteTextView
*/
@BindingAdapter("android:auto_adapter")
fun setAutoAdapter(view: AutoCompleteTextView, datList: List<String>) {
    view.setAdapter(
        ArrayAdapter(
            view.context,
            android.R.layout.simple_dropdown_item_1line,
            datList
        )
    )
    view.setOnClickListener {
        view.showDropDown()
    }
}

/*
* @param view ImageView
* @param image Image resource id
* Set Image Resource into ImageView
*/
@BindingAdapter("android:image")
fun setImage(view: ImageView, image: Int) {
    view.setImageResource(image)
}

/*
* @param view AutoCompleteTextView
* @param liveData MutableLive data to set the value of selected item
* Set an item click listener for auto complete TextView
*/
@BindingAdapter("android:item_selection")
fun setItemSelection(view: AutoCompleteTextView, liveData: MutableLiveData<String>) {
    view.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
        liveData.value = parent.getItemAtPosition(position).toString()
    }
}
