package com.kent.sample

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.kent.layouts.*

/**
 * Created by abduaziz on 2020-02-22 at 00:25.
 */

class MainFragment : BaseFragment() {

    override fun createView(context: Context): View? {
        return context.verticalLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)

            textView {
                layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                text = "Hello, Kent!"
            }

            recyclerView {
                layoutParams = LinearLayout.LayoutParams(matchParent, matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = RVAdapter()
            }
        }
    }
}