package com.kent.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.kent.fragment.menu.Menu
import com.kent.fragment.menu.MenuItem
import com.kent.fragment.theme.Theme
import com.kent.layouts.matchParent

/**
 * Created by abduaziz on 2020-02-23 at 04:46.
 */


abstract class BaseFragment {

    // arguments
    var args: Bundle? = null

    // parent activity
    var activity: Activity? = null
    lateinit var ctx: Context

    /*
    * Views:
    *  1. Parent view - top level parent view group
    *  2. Ghost view - used for alpha animation when this fragment is opened / closed
    *  3. Fragment view - hosts everything (regular views) this fragment shows to users
    * */
    /*internal*/ lateinit var parentView: FrameLayout
    internal var ghostView: View? = null
    internal var fragmentView: View? = null

    // swipe and remove
    var canSwipeBack: Boolean = false

    // Whether child fragments have their own implementation of back press events
    // if True, back press button does not hide this fragment
    var withBackPressed: Boolean = false

    /*
    * 'enter from' and 'exit to' animations
    * */
    var enterFrom = -1
    var exitTo = -1

    // theme: classic by default
    var theme = Theme.classic()

    /*
    * Called right after creation:
    * This fragment is not added to UI yet, will be added right after onCreate()
    * and inits parent and ghost view (if needed)
    * */
    open fun create() {
        parentView = FrameLayout(ctx)
        parentView.layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
        if (canSwipeBack || enterFrom >= 0 || exitTo >= 0) {
            ghostView = View(ctx)
            ghostView?.layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
            ghostView?.setBackgroundResource(R.drawable.ghost_drawable)
            parentView.addView(ghostView)
        }
    }

    // Creates a visible view and the view will be added to parent view
    abstract fun createView(context: Context): View?

    // called when successfully added to the stack
    open fun viewCreated(view: View?, args: Bundle?) {
        this.fragmentView = view
        // against unnecessary clicks from top fragment to bottom fragment
        this.fragmentView?.isClickable = true
        this.args = args
    }

    // called when parent activity resumed
    open fun resumed() {}

    open fun paused() {}

    // called when removed from the stack
    open fun removed() {}

    // creates menu for this fragment
    open fun createMenu(): Menu? = null

    // on menu item clicked
    open fun menuClicked(item: MenuItem) {}

    // alpha animate the ghost view
    internal fun ghostAlpha(alpha: Float) {
        ghostView?.alpha = alpha
    }

    // language changed: set string resources
    open fun languageChanged() {}

    // theme changed
    open fun themeChanged() {}

    open fun backPressed() {}

    open fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {}

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}

}