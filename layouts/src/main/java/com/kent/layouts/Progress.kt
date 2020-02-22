package com.kent.layouts

import android.view.ViewGroup
import android.widget.ProgressBar

/**
 * Created by abduaziz on 2020-02-23 at 06:27.
 */

/*
* Progress bars:
* - Linear progress
* - Circular progress
* Both of them are just styled versions of regular ProgressBar
* */

inline fun ViewGroup.linearProgress(init: ProgressBar.() -> Unit = {}): ProgressBar {
    val p = ProgressBar(context, null, android.R.style.Widget_DeviceDefault_Light_ProgressBar_Horizontal).apply(init)
    addView(p)
    return p
}

inline fun ViewGroup.circularProgress(init: ProgressBar.() -> Unit = {}): ProgressBar {
    val p = ProgressBar(context).apply(init)
    addView(p)
    return p
}