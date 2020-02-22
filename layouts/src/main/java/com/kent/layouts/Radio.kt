package com.kent.layouts

import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatRadioButton
import com.google.android.material.radiobutton.MaterialRadioButton

/**
 * Created by abduaziz on 2020-02-23 at 06:17.
 */

inline fun ViewGroup.radioButton(init: AppCompatRadioButton.() -> Unit = {}): AppCompatRadioButton {
    val a = AppCompatRadioButton(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.radioGroup(init: RadioGroup.() -> Unit = {}): RadioGroup {
    val r = RadioGroup(context).apply(init)
    addView(r)
    return r
}

inline fun ViewGroup.materialRadioButton(init: MaterialRadioButton.() -> Unit = {}): MaterialRadioButton {
    val a = MaterialRadioButton(context).apply(init)
    addView(a)
    return a
}