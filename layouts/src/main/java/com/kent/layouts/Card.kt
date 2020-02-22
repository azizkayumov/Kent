package com.kent.layouts

import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView

/**
 * Created by abduaziz on 2020-02-23 at 06:07.
 */

/*
* Cards
* - Regular card view
* - Material card view
* */

fun ViewGroup.cardView(init: CardView.() -> Unit = {}): CardView {
    val c = CardView(context).apply(init)
    addView(c)
    return c
}

fun ViewGroup.materialCardView(init: MaterialCardView.() -> Unit = {}): MaterialCardView {
    val m = MaterialCardView(context).apply(init)
    addView(m)
    return m
}