package com.kent.sample

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kent.layouts.*
import com.kent.layouts.viewgroup.lparams
import com.kent.layouts.viewgroup.verticalLayout

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            lparams(matchParent, matchParent)

            textView {
                layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                text = "Hello, World!"
            }

            button {
                layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                text = "Click me!"
                setOnClickListener {
                    toast("Hello, World!")
                }
            }
        }
    }
}
