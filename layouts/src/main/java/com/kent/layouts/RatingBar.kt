package com.kent.layouts

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRatingBar

/**
 * Created by abduaziz on 2020-02-23 at 06:30.
 */

inline fun ViewGroup.ratingBar(init: AppCompatRatingBar.() -> Unit = {}): AppCompatRatingBar {
    val r = AppCompatRatingBar(context)
    addView(r)
    return r
}