package com.kent.layouts

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/**
 * Created by abduaziz on 2020-02-23 at 06:30.
 */

inline fun ViewGroup.recyclerView(init: RecyclerView.() -> Unit = {}): RecyclerView {
    val r = RecyclerView(context).apply(init)
    addView(r)
    return r
}