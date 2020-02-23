package com.kent.sample

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kent.layouts.*
import com.kent.layouts.viewgroup.lparams
import com.kent.layouts.viewgroup.verticalLayout

class RegularActivity : AppCompatActivity() {

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
