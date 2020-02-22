package com.kent.layouts

import android.view.ViewGroup
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout

/**
 * Created by abduaziz on 2020-02-23 at 06:21.
 */

inline fun ViewGroup.tabItem(init: TabItem.() -> Unit = {}): TabItem {
    val t = TabItem(context).apply(init)
    addView(t)
    return t
}

inline fun ViewGroup.tabLayout(init: TabLayout.() -> Unit = {}): TabLayout {
    val t = TabLayout(context).apply(init)
    addView(t)
    return t
}

