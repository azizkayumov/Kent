package com.kent.sample

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.kent.layouts.matchParent

class SampleActivity : AppCompatActivity() {

    // root view: all fragments are added, removed
    private lateinit var rootView: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootView = FrameLayout(this)
        rootView.layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
        setContentView(rootView)

        val main = MainFragment()
        main.activity = this
        main.ctx = rootView.context

        main.create()
        val fragmentView = main.createView(rootView.context)
        main.parentView.addView(fragmentView)

        rootView.addView(main.parentView)
    }
}
