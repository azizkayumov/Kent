package com.kent.sample

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.kent.fragment.BaseFragment

/**
 * Created by abduaziz on 2020-02-22 at 00:25.
 */

class MainFragment : BaseFragment() {

    override fun createView(context: Context): View? {
        return mainUI()
    }
}