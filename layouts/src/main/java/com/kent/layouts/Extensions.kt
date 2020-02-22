package com.kent.layouts

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Created by abduaziz on 2020-02-21 at 23:29.
 */

val TAG = "Kent Layouts"

fun log(l: String) {
    Log.d(TAG, l)
}

fun longLog(l: String) {
    val maxLogSize = 500
    for (i in 0..l.length / maxLogSize) {
        val start = i * maxLogSize
        var end = (i + 1) * maxLogSize
        end = if (end > l.length) l.length else end
        if (i == 0)
            Log.d(TAG, l.substring(start, end))
        else
            Log.d(TAG, "               " + l.substring(start, end))
    }
}


