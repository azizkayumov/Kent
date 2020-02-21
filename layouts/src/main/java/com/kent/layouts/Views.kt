package com.kent.layouts

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

/**
 * Created by abduaziz on 2020-02-22 at 01:13.
 */

fun Context.frameLayout(init: FrameLayout.() -> Unit): FrameLayout {
    return FrameLayout(this).apply(init)
}

fun ViewGroup.textView(init: TextView.() -> Unit): TextView {
    val t = TextView(context).apply(init)
    addView(t)
    return t
}