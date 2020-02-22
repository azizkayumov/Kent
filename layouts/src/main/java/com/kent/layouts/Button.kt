package com.kent.layouts

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Created by abduaziz on 2020-02-23 at 06:02.
 */

/*
* Buttons
* - Regular button
* - Material button
* - Floating action button
* */

fun ViewGroup.button(init: AppCompatButton.() -> Unit = {}): AppCompatButton {
    val a = AppCompatButton(context).apply(init)
    addView(this)
    return a
}

fun ViewGroup.materialButton(init: MaterialButton.() -> Unit = {}): MaterialButton {
    val m = MaterialButton(context).apply(init)
    addView(m)
    return m
}

fun ViewGroup.floatingActionButton(init: FloatingActionButton.() -> Unit = {}): FloatingActionButton {
    val f = FloatingActionButton(context).apply(init)
    addView(f)
    return f
}

/*
* Properties
* */

fun FloatingActionButton.setIconColor(context: Context, color: Int) {
    this.setColorFilter(ContextCompat.getColor(context, color), android.graphics.PorterDuff.Mode.SRC_IN)
}