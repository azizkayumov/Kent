package com.kent.layouts

import android.content.Context
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout

/**
 * Created by abduaziz on 2020-02-22 at 06:40.
 */

inline fun Context.frameLayout(init: FrameLayout.() -> Unit): FrameLayout {
    return FrameLayout(this).apply(init)
}

inline fun Context.verticalLayout(init: LinearLayout.() -> Unit): LinearLayout {
    return LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        init()
    }
}

inline fun Context.horizontalLayout(init: LinearLayout.() -> Unit): LinearLayout {
    return LinearLayout(this).apply {
        orientation = LinearLayout.HORIZONTAL
        init()
    }
}

inline fun Context.relativeLayout(init: RelativeLayout.() -> Unit): RelativeLayout {
    return RelativeLayout(this).apply(init)
}

inline fun Context.gridLayout(init: GridLayout.() -> Unit): GridLayout {
    return GridLayout(this).apply(init)
}