package com.kent.sample

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.kent.layouts.*
import com.kent.layouts.toolbar
import com.kent.layouts.viewgroup.coordinatorLayout
import com.kent.layouts.viewgroup.lparams
import kotlinx.android.synthetic.main.activity_basic.*

class BasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
//        setContentView(R.layout.activity_basic)
//        setSupportActionBar(toolbar)
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    // It is better to write UI code in a separate file
    // move this function to separate file as extension function of BasicActivity
    private fun initUI() {
        coordinatorLayout {
            lparams(matchParent, matchParent)
            setBackgroundColor(Color.WHITE)

            appBarLayout {
                layoutParams = CoordinatorLayout.LayoutParams(matchParent, wrapContent)
                setTheme(R.style.AppTheme_AppBarOverlay)

                toolbar {
                    layoutParams = AppBarLayout.LayoutParams(matchParent, dip(56))
                    backgroundColorResource = R.color.colorPrimary
                    popupTheme = R.style.AppTheme_PopupOverlay
                    setSupportActionBar(this)
                }
            }

            floatingActionButton {
                layoutParams = CoordinatorLayout.LayoutParams(wrapContent, wrapContent).apply {
                    gravity = Gravity.BOTTOM or Gravity.END
                    margin = dip(16)
                }
                setImageResource(android.R.drawable.ic_dialog_email)

                setOnClickListener {
                    Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
        }
    }
}
