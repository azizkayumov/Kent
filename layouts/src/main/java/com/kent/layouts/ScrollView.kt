package com.kent.layouts

import android.view.ViewGroup
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView

/**
 * Created by abduaziz on 2020-02-23 at 07:42.
 */

fun ViewGroup.scrollView(init: ScrollView.() -> Unit): ScrollView {
    val s = ScrollView(context).apply(init)
    addView(s)
    return s
}

fun ViewGroup.nestedScrollView(init: NestedScrollView.() -> Unit): NestedScrollView {
    val s = NestedScrollView(context).apply(init)
    addView(s)
    return s
}