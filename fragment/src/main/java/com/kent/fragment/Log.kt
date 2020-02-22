package com.kent.fragment

import android.util.Log

/**
 * Created by abduaziz on 2020-02-23 at 05:14.
 */

val TAG = "KENT"

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