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

inline fun BaseFragment.frameLayout(init: FrameLayout.() -> Unit): FrameLayout {
    return ctx.frameLayout(init)
}

inline fun Context.verticalLayout(init: LinearLayout.() -> Unit): LinearLayout {
    return LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        init()
    }
}

inline fun BaseFragment.verticalLayout(init: LinearLayout.() -> Unit): LinearLayout {
    return ctx.verticalLayout(init)
}

inline fun Context.horizontalLayout(init: LinearLayout.() -> Unit): LinearLayout {
    return LinearLayout(this).apply {
        orientation = LinearLayout.HORIZONTAL
        init()
    }
}

inline fun BaseFragment.horizontalLayout(init: LinearLayout.() -> Unit): LinearLayout {
    return ctx.horizontalLayout(init)
}

inline fun Context.relativeLayout(init: RelativeLayout.() -> Unit): RelativeLayout {
    return RelativeLayout(this).apply(init)
}

inline fun BaseFragment.relativeLayout(init: RelativeLayout.() -> Unit): RelativeLayout {
    return ctx.relativeLayout(init)
}

inline fun Context.gridLayout(init: GridLayout.() -> Unit): GridLayout {
    return GridLayout(this).apply(init)
}

inline fun BaseFragment.gridLayout(init: GridLayout.() -> Unit): GridLayout {
    return ctx.gridLayout(init)
}