package com.utils

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.R


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

@SuppressLint("SetTextI18n")
@BindingAdapter("android:price_string")
fun setPriceString(view: TextView, price: Int) {
    view.text =
        view.context.getString(R.string.price) + (price / 1000) + view.context.getString(R.string.k)
}

