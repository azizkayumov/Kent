package com.kent.sample

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.kent.layouts.*

/**
 * Created by abduaziz on 2020-02-22 at 00:25.
 */

class MainFragment : BaseFragment() {

    override fun createView(context: Context): View? {
        return context.frameLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)

            textView {
                layoutParams = FrameLayout.LayoutParams(wrapContent, wrapContent).apply {
                    gravity = Gravity.CENTER
                }
                this.text = "Hello, Kent!"
            }
        }
    }
}