package com.kent.layouts

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.DisplayMetrics
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import com.kent.layouts.menu.Menu
import com.kent.layouts.menu.MenuFragment
import com.kent.layouts.menu.MenuItem
import com.kent.layouts.menu.OnMenuClickListener

/**
 * Created by abduaziz on 2020-02-22 at 04:28.
 */


open class BaseActivity : Activity() {

    // root view: all fragments are added, removed
    private lateinit var rootView: FrameLayout

    // back stack
    private val fragments = arrayListOf<BaseFragment>()

    // menu fragment, it is created on demand
    val menuFragment = MenuFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        // add root view
        rootView = FrameLayout(this)
        rootView.layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
        setContentView(rootView)
        touchSlop = ViewConfiguration.get(this).scaledTouchSlop
    }

    private var touchSlop = 0

    private var initialX = 0f
    private var initialY = 0f

    // currentX
    private var cX = 0F
    // currentY
    private var cY = 0F
    // currentTime
    private var cTime = 0L
    // currentSpeed
    private var cSpeed = 0F
    // swiping begins
    var swiping = false

    // screen width
    internal val screenWidth by lazy {
        val displayMetrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        displayMetrics.widthPixels
    }

    override fun dispatchTouchEvent(motionEvent: MotionEvent?): Boolean {
        if (motionEvent == null || topFragment()?.canSwipeBack == false)
            return super.dispatchTouchEvent(motionEvent)

        val w = topFragment()?.fragmentView?.width ?: 0
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                initialX = motionEvent.rawX
                initialY = motionEvent.rawY
                cX = motionEvent.rawX
                cY = motionEvent.rawY
                cTime = motionEvent.downTime
                swiping = false
                //lastTouchTime = SystemClock.elapsedRealtime()
            }
            MotionEvent.ACTION_MOVE -> {
                val xDelta = motionEvent.rawX - initialX
                val yDelta = motionEvent.rawY - initialY
                if (swiping || topFragment()?.exitTo == RIGHT &&
                    Math.abs(xDelta) > touchSlop &&
                    Math.abs(yDelta) * 2 <= xDelta
                ) {
                    topFragment()?.fragmentView?.x = if (xDelta >= 0F) xDelta else 0F
                    cSpeed = (cX - motionEvent.rawX) / (cTime - motionEvent.eventTime + 0.1F)
                    val alphaX = topFragment()?.fragmentView?.x ?: 0F
                    topFragment()?.ghostAlpha((w - alphaX) / (w + 1))
                    cX = motionEvent.rawX
                    swiping = true
                    return true
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                val xDelta = motionEvent.rawX - initialX
                val yDelta = motionEvent.rawY - initialY
                if (topFragment()?.exitTo == RIGHT &&
                    Math.abs(xDelta) > touchSlop &&
                    Math.abs(yDelta) * 2 <= xDelta &&
                    swiping
                ) {

                    val speed = (cX - motionEvent.rawX) / (cTime - motionEvent.eventTime + 0.0001F)
                    //Log.d("PagerX", "speed = $speed, cSpeed = $cSpeed")
                    if (topFragment()?.fragmentView?.x ?: 0F >= rootView.width / 2 // scrolled till the half
                        || cSpeed >= 1 // really fast, let's remove
                        || (speed < -0.1 && cSpeed >= 0.5 // some magic
                                )
                    ) {
                        topFragment()?.fragmentView?.animate()
                        removeTopFragment(true)
                        return true
                    }
                }
                topFragment()?.fragmentView?.animate()?.x(0f)
                if (swiping) return true
            }
        }
        //if (SystemClock.elapsedRealtime() - lastTouchTime <= 1000) return true
        return super.dispatchTouchEvent(motionEvent)
    }

    override fun onBackPressed() {
        if (topFragment()?.withBackPressed == true) {
            topFragment()?.backPressed()
            return
        }
        if (removeTopFragment(topFragment()?.exitTo ?: -1 >= 0)) {
            return
        }
        super.onBackPressed()
    }

    fun openFragment(baseFragment: BaseFragment, animate: Boolean = false) {
        // prevent double tap or opening two same fragment sequentially
        if (topFragment()?.javaClass == baseFragment.javaClass) return

        topFragment()?.paused()

        hideSoftInput()

        baseFragment.activity = this
        //baseFragment.backStackPosition = rootView.childCount
        baseFragment.ctx = rootView.context
        //val start = System.currentTimeMillis()
        baseFragment.create()
        val fragmentView = baseFragment.createView(rootView.context)
        //longLog("Create() and createView() took ${System.currentTimeMillis() - start} millis")
        if (fragmentView != null) {
            fragments.add(baseFragment)
            baseFragment.parentView.addView(fragmentView)
            rootView.addView(baseFragment.parentView)

            // view created, finally
            baseFragment.viewCreated(fragmentView, baseFragment.args)

            // enter animation
            if (animate) {
                when (baseFragment.enterFrom) {
                    RIGHT -> {
                        val backgroundView = null
                        enterFromRight(fragmentView, backgroundView).apply {
                            addListener(object : Animator.AnimatorListener {
                                override fun onAnimationCancel(animation: Animator?) {}
                                override fun onAnimationRepeat(animation: Animator?) {}
                                override fun onAnimationStart(animation: Animator?) {
                                    baseFragment.ghostView?.visibility = View.GONE
                                }

                                override fun onAnimationEnd(animation: Animator?) {
                                    baseFragment.ghostView?.visibility = View.VISIBLE
                                    // fragment resuming
                                    baseFragment.resumed()
                                }
                            })
                        }.start()
                    }
                    BOTTOM -> {
                        enterFromBottom(
                            rootView.height.toFloat(),
                            fragmentView,
                            baseFragment.ghostView
                        ).apply {
                            addListener(object : Animator.AnimatorListener {
                                override fun onAnimationCancel(animation: Animator?) {}
                                override fun onAnimationRepeat(animation: Animator?) {}
                                override fun onAnimationStart(animation: Animator?) {
                                    //baseFragment.ghostView?.visibility = View.GONE
                                }

                                override fun onAnimationEnd(animation: Animator?) {
                                    //baseFragment.ghostView?.visibility = View.VISIBLE
                                    // fragment resuming
                                    baseFragment.resumed()
                                }
                            })
                        }.start()
                    }
                    FADE_IN -> {
                        enterFadingIn(
                            fragmentView,
                            baseFragment.ghostView
                        ).apply {
                            addListener(object : Animator.AnimatorListener {
                                override fun onAnimationCancel(animation: Animator?) {}
                                override fun onAnimationRepeat(animation: Animator?) {}
                                override fun onAnimationStart(animation: Animator?) {
                                    //baseFragment.ghostView?.visibility = View.GONE
                                }

                                override fun onAnimationEnd(animation: Animator?) {
                                    //baseFragment.ghostView?.visibility = View.VISIBLE
                                    // fragment resuming
                                    baseFragment.resumed()
                                }
                            })
                        }.start()
                    }
                    else -> {
                        baseFragment.resumed()
                    }
                }
            } else {
                // fragment resuming
                baseFragment.resumed()
            }
        }
    }

    // removes only the top fragment
    // and set the baseFragment fragment as the top fragment
    fun replaceFragment(baseFragment: BaseFragment, animate: Boolean = false) {
        if (animate) {
            openFragment(baseFragment, animate)
            Handler().postDelayed({
                // the top fragment has ended its animation
                // remove the foreground fragment
                foregroundFragment()?.let {
                    removeFragment(it, false, false)
                }
            }, SHORT_ANIMATION)
        } else {
            topFragment()?.let {
                removeFragment(it, false)
            }
            openFragment(baseFragment)
        }
    }

    // removes all base fragment in the back stack
    // and set the baseFragment as the top fragment
    fun replaceAllFragments(baseFragment: BaseFragment, animate: Boolean = false) {
        fragments.forEach {
            it.removed()
        }
        fragments.clear()
        rootView.removeAllViews()
        openFragment(baseFragment, animate)
    }

    private fun removeFragment(baseFragment: BaseFragment, animate: Boolean, resumeTopFragment: Boolean = true) {
        hideSoftInput()
        val pos = rootView.indexOfChild(baseFragment.parentView)
        if (pos >= 0 && pos < fragments.size) {
            fragments.removeAt(pos)
            if (animate) {
                when (baseFragment.exitTo) {
                    RIGHT -> {
                        val backgroundView = null
                        rootView.getChildAt(pos)?.let {
                            exitToRight(
                                rootView.width.toFloat(),
                                baseFragment.fragmentView,
                                backgroundView,
                                baseFragment.ghostView,
                                swiping
                            ).apply {
                                addListener(object : Animator.AnimatorListener {
                                    override fun onAnimationCancel(animation: Animator?) {}
                                    override fun onAnimationRepeat(animation: Animator?) {}
                                    override fun onAnimationStart(animation: Animator?) {}
                                    override fun onAnimationEnd(animation: Animator?) {
                                        baseFragment.removed()
                                        rootView.removeViewAt(pos)
                                        if (resumeTopFragment) resumeTopFragment()
                                    }
                                })
                            }.start()
                        }
                    }
                    BOTTOM -> {
                        rootView.getChildAt(pos)?.let {
                            exitToBottom(
                                rootView.height.toFloat(),
                                baseFragment.fragmentView,
                                baseFragment.ghostView
                            ).apply {
                                addListener(object : Animator.AnimatorListener {
                                    override fun onAnimationCancel(animation: Animator?) {}
                                    override fun onAnimationRepeat(animation: Animator?) {}
                                    override fun onAnimationStart(animation: Animator?) {}
                                    override fun onAnimationEnd(animation: Animator?) {
                                        baseFragment.removed()
                                        rootView.removeViewAt(pos)
                                        if (resumeTopFragment) resumeTopFragment()
                                    }
                                })
                            }.start()
                        }
                    }
                    FADE_OUT -> {
                        rootView.getChildAt(pos)?.let {
                            exitFadeOut(
                                baseFragment.fragmentView,
                                baseFragment.ghostView
                            ).apply {
                                addListener(object : Animator.AnimatorListener {
                                    override fun onAnimationCancel(animation: Animator?) {}
                                    override fun onAnimationRepeat(animation: Animator?) {}
                                    override fun onAnimationStart(animation: Animator?) {}
                                    override fun onAnimationEnd(animation: Animator?) {
                                        baseFragment.removed()
                                        rootView.removeViewAt(pos)
                                        if (resumeTopFragment) resumeTopFragment()
                                    }
                                })
                            }.start()
                        }
                    }
                }
                swiping = false
            } else {
                if (baseFragment is MenuFragment) {
                    baseFragment.removed()
                    Handler().postDelayed({
                        rootView.removeViewAt(pos)
                        if (resumeTopFragment) resumeTopFragment()
                    }, LONG_ANIMATION)
                } else {
                    baseFragment.removed()
                    rootView.removeViewAt(pos)
                    if (resumeTopFragment) resumeTopFragment()
                }
            }
        }
    }

    private fun resumeTopFragment() {
        // or remaining top fragment is resuming
        if (fragments.isNotEmpty()) {
            val topFragment = fragments[fragments.size - 1]
            topFragment.resumed()
        }
    }

    // return if top fragment was removed
    fun removeTopFragment(animate: Boolean, resumeTopFragment: Boolean = true): Boolean {
        if (fragments.size >= 1) {
            val topFragment = fragments[fragments.size - 1]
            val shouldAnimate = if (topFragment is MenuFragment) false else animate
            removeFragment(topFragment, shouldAnimate, resumeTopFragment)

            if (fragments.isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAndRemoveTask()
                } else {
                    finish()
                }
            }
            return true
        }
        return false
    }

    fun <T> removeFragmentType(clazz: Class<T>) {
        fragments.find {
            it.javaClass == clazz
        }?.let {
            removeFragment(it, false, false)
        }
    }

    fun topFragment(): BaseFragment? {
        if (fragments.isNotEmpty())
            return fragments[fragments.size - 1]
        return null
    }

    // the one just under the top fragment
    fun foregroundFragment(): BaseFragment? {
        if (fragments.size > 2)
            return fragments[fragments.size - 2]
        return null
    }

    fun getFragmentAt(backStackPosition: Int): BaseFragment? {
        if (backStackPosition >= 0 && backStackPosition < fragments.size)
            return fragments[backStackPosition]
        return null
    }

    // menu
    fun showMenu(menu: Menu) {
        menuFragment.menu = menu
        menuFragment.activity = this
        //menuFragment.backStackPosition = rootView.childCount
        menuFragment.ctx = rootView.context
        menuFragment.create()
        val menuView = menuFragment.createView(rootView.context)
        if (menuView != null) {

            menuView.setOnClickListener {
                closeMenu()
            }

            fragments.add(menuFragment)
            menuFragment.parentView.addView(menuView)
            rootView.addView(menuFragment.parentView)

            menuFragment.onMenuClickListener = object : OnMenuClickListener {
                override fun onMenuClick(item: MenuItem) {
                    closeMenu()
                    Handler().postDelayed({
                        topFragment()?.menuClicked(item)
                    }, LONG_ANIMATION + 1)
                }
            }
            menuFragment.viewCreated(menuView, null)
        }
    }

    fun closeMenu() {
        topFragment()?.let {
            if (it is MenuFragment) {
                removeTopFragment(false, false)
            }
        }
    }

    // hide keyboard
    fun hideSoftInput() {
        if (windowToken != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        } else {
            longLog("Keyboard close error")
        }
    }

    // show keyboard
    private var windowToken: IBinder? = null

    fun showSoftKeyboard(et: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        et.requestFocus()
        et.isCursorVisible = true
        this.windowToken = et.windowToken
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        // only the top fragment may receive the results ;-)
        topFragment()?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        topFragment()?.onActivityResult(requestCode, resultCode, data)
    }
}