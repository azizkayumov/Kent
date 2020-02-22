package com.kent.layouts.viewgroup

import android.content.Context
import android.view.ViewGroup
import android.widget.RelativeLayout

/**
 * Created by abduaziz on 2020-02-23 at 06:39.
 */

inline fun Context.relativeLayout(init: RelativeLayout.() -> Unit): RelativeLayout {
    return RelativeLayout(this).apply(init)
}

inline fun ViewGroup.relativeLayout(init: RelativeLayout.() -> Unit): RelativeLayout {
    val f = RelativeLayout(context).apply(init)
    addView(f)
    return f
}