package com.kent.sample

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import com.kent.layouts.*
import com.kent.layouts.viewgroup.frameLayout

/**
 * Created by abduaziz on 2020-02-23 at 07:53.
 */

fun MainFragment.mainUI() = ctx.frameLayout {
    layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)

    frameLayout {
        layoutParams = FrameLayout.LayoutParams(matchParent, dip(56))
        backgroundColorResource = R.color.colorPrimary

        imageView {
            layoutParams = FrameLayout.LayoutParams(dip(56), dip(56))
            setRippleEffect()
            padding = dip(16)
            setImageResource(R.drawable.ic_menu_nav)
            setIconColor(Color.WHITE)
        }

        textView {
            layoutParams = FrameLayout.LayoutParams(matchParent, matchParent).apply {
                leftMargin = dip(56)
                rightMargin = dip(56)
            }
            setStyle(ctx, android.R.style.TextAppearance_DeviceDefault_Widget_ActionBar_Title)
            gravity = Gravity.CENTER_VERTICAL
            textColorResource = R.color.colorAccent
            text = "Newsfeed"
        }

        imageView {
            layoutParams = FrameLayout.LayoutParams(dip(56), dip(56)).apply {
                gravity = Gravity.END
            }
            setRippleEffect()
            padding = dip(16)
            setImageResource(R.drawable.ic_menu)
            setIconColor(Color.WHITE)
        }
    }

    view {
        layoutParams = FrameLayout.LayoutParams(matchParent, dip(2)).apply {
            topMargin = dip(56)
        }
        setBackgroundResource(R.drawable.pre_lollipop_elevation)
    }
}