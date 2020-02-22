package com.kent.layouts

import android.text.InputFilter
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by abduaziz on 2020-02-23 at 06:23.
 */

inline fun ViewGroup.editText(init: AppCompatEditText.() -> Unit = {}): AppCompatEditText {
    val a = AppCompatEditText(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.textInputEditText(init: TextInputEditText.() -> Unit = {}): TextInputEditText {
    val a = TextInputEditText(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.textInputLayout(init: TextInputLayout.() -> Unit = {}): TextInputLayout {
    val t = TextInputLayout(context).apply(init)
    addView(t)
    return t
}

/*
* Properties
* */

fun EditText.maxLength(limit: Int) {
    filters = arrayOf(InputFilter.LengthFilter(limit))
}

