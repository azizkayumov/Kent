package com.kent.sample.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kent.fragment.BaseFragment
import com.kent.layouts.cardView
import com.kent.layouts.dip
import com.kent.layouts.textView
import com.kent.layouts.viewgroup.lparams
import com.kent.layouts.wrapContent

/**
 * Created by abduaziz on 2020-02-22 at 00:25.
 */

class MainFragment : BaseFragment() {

    override fun createView(context: Context): View? {
        return mainUI()
    }

    override fun viewCreated(view: View?, args: Bundle?) {
        super.viewCreated(view, args)


        val cardView = ctx.cardView {
            lparams(dip(100), wrapContent)
        }
    }
}