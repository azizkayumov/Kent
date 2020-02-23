package com.kent.layouts

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.test.core.app.ApplicationProvider
import com.kent.layouts.viewgroup.verticalLayout
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.random.Random

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun addView_viewCount() {
        val view_count = Random.nextInt(0, 10000)

        val context = ApplicationProvider.getApplicationContext<Context>()
        val r = context.verticalLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)

            for (i in 0 until view_count) {
                textView {
                    layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                    text = "Text $i"
                }
            }
        }
        assertEquals(view_count, r.childCount)
    }
}