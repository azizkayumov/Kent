package com.kent.layouts

import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

/**
 * Created by abduaziz on 2020-02-23 at 05:59.
 */

/*
* Navigation drawers
* - Modal drawer (standard with gravity = left)
* - Bottom drawer (used with bottom app bar)
* */

inline fun ViewGroup.navigationView(init: NavigationView.() -> Unit = {}): NavigationView {
    val n = NavigationView(context).apply(init)
    addView(n)
    return n
}

inline fun ViewGroup.bottomNavigationView(init: BottomNavigationView.() -> Unit = {}): BottomNavigationView {
    val n = BottomNavigationView(context).apply(init)
    addView(n)
    return n
}
