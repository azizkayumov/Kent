package com.kent.layouts

import android.view.ViewGroup
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * Created by abduaziz on 2020-02-23 at 06:11.
 */

/*
* Chips
* Chips group
* */
fun ViewGroup.chip(init: Chip.() -> Unit = {}): Chip {
    val c = Chip(context).apply(init)
    addView(c)
    return c
}

fun ViewGroup.chipGroup(init: ChipGroup.() -> Unit = {}): ChipGroup {
    val c = ChipGroup(context).apply(init)
    addView(c)
    return c
}