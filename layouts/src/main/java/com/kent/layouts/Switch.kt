package com.kent.layouts

import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.switchmaterial.SwitchMaterial

/**
 * Created by abduaziz on 2020-02-23 at 06:20.
 */

inline fun ViewGroup.switch(init: SwitchCompat.() -> Unit = {}): SwitchCompat {
    val s = SwitchCompat(context).apply(init)
    addView(s)
    return s
}

inline fun ViewGroup.switchMaterial(init: SwitchMaterial.() -> Unit = {}): SwitchMaterial {
    val s = SwitchMaterial(context).apply(init)
    addView(s)
    return s
}