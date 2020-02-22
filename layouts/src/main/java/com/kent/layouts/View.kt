package com.kent.layouts

import android.content.Context
import android.view.View
import android.view.ViewGroup

/**
 * Created by abduaziz on 2020-02-23 at 08:07.
 */

fun Context.view(init: View.() -> Unit): View {
    return View(this).apply(init)
}

fun ViewGroup.view(init: View.() -> Unit): View {
    val v = View(context).apply(init)
    addView(v)
    return v
}