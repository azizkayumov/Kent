package com.kent.layouts

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import com.google.android.material.checkbox.MaterialCheckBox

/**
 * Created by abduaziz on 2020-02-23 at 06:09.
 */

inline fun ViewGroup.checkBox(init: AppCompatCheckBox.() -> Unit = {}): AppCompatCheckBox {
    val a = AppCompatCheckBox(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.materialCheckBox(init: MaterialCheckBox.() -> Unit = {}): MaterialCheckBox {
    val a = MaterialCheckBox(context).apply(init)
    addView(a)
    return a
}