package com.kent.layouts

import android.view.ViewGroup
import android.widget.DatePicker

/**
 * Created by abduaziz on 2020-02-23 at 06:14.
 */

/*
* Date picker
*/
inline fun ViewGroup.datePicker(init: DatePicker.() -> Unit = {}): DatePicker {
    val d = DatePicker(context).apply(init)
    addView(d)
    return d
}

// Should I include MaterialDatePicker which is Dialog?