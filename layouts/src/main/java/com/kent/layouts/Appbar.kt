package com.kent.layouts

import android.view.ViewGroup
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomappbar.BottomAppBar

/**
 * Created by abduaziz on 2020-02-23 at 05:43.
 */

inline fun ViewGroup.appBarLayout(init: AppBarLayout.() -> Unit): AppBarLayout {
    val a = AppBarLayout(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.collapsingToolbarLayout(init: CollapsingToolbarLayout.() -> Unit): CollapsingToolbarLayout {
    val a = CollapsingToolbarLayout(context).apply(init)
    addView(a)
    return a
}

inline fun ViewGroup.materialToolbar(init: MaterialToolbar.() -> Unit): MaterialToolbar {
    val a = MaterialToolbar(context).apply(init)
    addView(a)
    return a
}

fun ViewGroup.bottomAppBar(init: BottomAppBar.() -> Unit): BottomAppBar {
    val b = BottomAppBar(context).apply(init)
    addView(b)
    return b
}