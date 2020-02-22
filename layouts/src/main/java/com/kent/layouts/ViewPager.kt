package com.kent.layouts

import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

/**
 * Created by abduaziz on 2020-02-23 at 06:31.
 */

inline fun ViewGroup.viewPager(init: ViewPager.() -> Unit = {}): ViewPager {
    val v = ViewPager(context).apply(init)
    addView(v)
    return v
}

inline fun ViewGroup.viewPager2(init: ViewPager2.() -> Unit = {}): ViewPager2 {
    val v = ViewPager2(context).apply(init)
    addView(v)
    return v
}